<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="../../js/datepicker/WdatePicker.js"></script>
</head>
<body>

<form method="post">
    <input type="hidden" name="id" value="${obj.id}"/>
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#" onclick="formSubmit('update.action','_self');">确定</a></li>
                        <li id="back"><a href="list.action">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">

        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    修改装箱信息
                </div>
            </div>
        </div>
        <div>


            <div>
                <table class="commonTable" cellspacing="1">

                    <tr>
                        <td class="columnTitle_mustbe">卖方：</td>
                        <td class="tableContent"><textarea style="height: 120px;" name="seller">${obj.seller}</textarea></td>
                        <td class="columnTitle_mustbe">买方：</td>
                        <td class="tableContent"><textarea style="height: 120px;" name="buyer">${obj.buyer}</textarea></td>
                    </tr>

                    <tr>
                        <td class="columnTitle_mustbe">发票号：</td>
                        <td class="tableContent"><input type="text" name="invoiceNo" value="${obj.invoiceNo}"/></td>
                        <td class="columnTitle_mustbe">签单日期：</td>
                        <td class="tableContent">
                            <input type="text" style="width: 90px;" name="invoiceDate" value="<fmt:formatDate value="${obj.invoiceDate}" pattern="yyyy-MM-dd"/> "
                                   onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="columnTitle_mustbe">唛头：</td>
                        <td class="tableContent"><textarea style="height: 120px;" name="marks">${obj.marks}</textarea></td>
                        <td class="columnTitle_mustbe">描述：</td>
                        <td class="tableContent"><textarea style="height: 120px;" name="descriptions">${obj.descriptions}</textarea></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox1">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    报运信息
                </div>
            </div>
        </div>
        <div>

            <div style="text-align:left;">
                ${divData}
            </div>
        </div>
    </div>
</form>
</body>
</html>

