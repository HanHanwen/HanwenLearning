'合并当前目录下出去目标表外的其他execl表
Sub 合并当前目录下所有工作簿的全部工作表()
Dim mypath, myname, awbname
Dim wb As Workbook, wbn As String
Dim g As Long
Dim num As Long
Dim box As String
Application.ScreenUpdating = False
mypath = ActiveWorkbook.Path
'MsgBox "mypath:" & mypath
myname = Dir(mypath & "/" & "*.xlsx")
'MsgBox "myname:" & myname
awbname = ActiveWorkbook.Name
'MsgBox "awbname:" & awbname
num = 0
Do While myname <> ""
If myname <> awbname Then
Set wb = Workbooks.Open(mypath & "/" & myname)
num = num + 1
With Workbooks(1).ActiveSheet
.Cells(.Range("a65536").End(xlUp).Row + 2, 1) = Left(myname, Len(myname) - 4)
For g = 1 To Sheets.Count
wb.Sheets(g).UsedRange.Copy .Cells(.Range("a65536").End(xlUp).Row + 1, 1)
Next
wbn = wbn & Chr(13) & wb.Name
wb.Close False
End With
End If
myname = Dir
Loop
Range("a1").Select
Application.ScreenUpdating = True
MsgBox "共合并了" & num & "个工作薄下的全部工作表。如下：" & Chr(13) & wbn, vbInformation, "提示"
End Sub
