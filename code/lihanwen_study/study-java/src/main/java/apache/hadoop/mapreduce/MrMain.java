package apache.hadoop.mapreduce;


import com.hadoop.mapreduce.LzoTextInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.orc.mapred.OrcStruct;
import org.apache.orc.mapreduce.OrcOutputFormat;

import java.net.URI;

/**
 * @author :  lihanwen
 * @date   :  2020-02-26
 */
public class MrMain {
    public static void main(String[] args) throws Exception{
        /**
         * 用户需事先输入5个入参
         */
        System.out.println("args count is " + args.length);
        //输入路径
        String InDir    = args[0];
        //输出路径
        String OutDir   = args[1];
        //日期
        String DealDate = args[2];
        //任务名称
        String JobName  = args[3];
        //解析字段
        String InField  = args[4];

        Configuration configuration = new Configuration();
        configuration.set("inFields", InField);

        Job job = Job.getInstance(configuration, JobName);
        job.setJarByClass(MrMain.class);

        Path InPath = new Path(InDir +"/"+ DealDate.replaceAll("-","")+"/");
        Path In     = new Path(InDir +"/"+ DealDate.replaceAll("-","")+"/*");
        Path Out    = new Path(OutDir + "/create_dt=" + DealDate);

        FileSystem fileSystem = FileSystem.get(new URI(In.toString()), new Configuration());

        //判断源文件路径是否存在，若是存在则进行后续处理
        if(fileSystem.exists(InPath)){
            //如果输出路径文件存在，则先删除输出路径文件
            if(fileSystem.exists(Out)){
                fileSystem.delete(Out, true);
            }

            FileInputFormat.setInputPaths(job, In);
            FileOutputFormat.setOutputPath(job, Out);

            job.setMapperClass(MrMapper.class);
            job.setReducerClass(MrReducer.class);

            job.setInputFormatClass(LzoTextInputFormat.class);
            job.setOutputFormatClass(OrcOutputFormat.class);


            job.setMapOutputKeyClass(NullWritable.class);
            job.setMapOutputValueClass(Text.class);
            job.setOutputKeyClass(NullWritable.class);
            job.setOutputValueClass(OrcStruct.class);

        }

        if(job.waitForCompletion(true)){
            System.exit(0);
        }else {
            System.exit(1);
        }

    }
}
