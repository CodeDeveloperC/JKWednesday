<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
        function setFactoryName(val) {
            var ele=document.getElementById("factoryName");
            ele.value=val;
        }
    </script>
</head>
<body>
<form method="post">
    <input type="hidden" name="contractId" value="${contractId}"/>
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#" onclick="formSubmit('insert.action','_self');">确定</a></li>
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
                    新增货物信息
                </div>
            </div>
        </div>
        <div>


            <div>
                <table class="commonTable" cellspacing="1">
                    <tr>
                        <td class="columnTitle_mustbe">厂家名称：</td>
                        <td class="tableContent">
                            <select name="factoryId" onchange="setFactoryName(this.options[this.selectedIndex].text);">
                                <option value="">--请选择--</option>
                                <c:forEach items="${factoryList}" var="f">
                                    <option value="${f.id}">${f.factoryName}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="factoryName" id="factoryName" value=""/>
                        </td>
                        <td class="columnTitle_mustbe">货号：</td>
                        <td class="tableContent"><input type="text" name="productNo"/></td>
                    </tr>

                    <tr>
                        <td class="columnTitle_mustbe">货物照片：</td>
                        <td class="tableContent"><input type="text" name="productImage"/></td>

                    </tr>

                    <tr>
                        <td class="columnTitle_mustbe">数量：</td>
                        <td class="tableContent">
                            <input type="text" name="cnumber"/></td>
                        </td>
                        <td class="columnTitle_mustbe">包装单位：</td>
                        <td class="tableContentAuto">
                            <input type="text" name="packingUnit"/></td>
                        </td>
                    </tr>

                    <tr>
                        <td class="columnTitle_mustbe">装率：</td>
                        <td class="tableContent">
                            <input type="text" name="loadingRate"/></td>
                        </td>
                        <td class="columnTitle_mustbe">箱数：</td>
                        <td class="tableContent">
                            <input type="text" name="boxNum"/></td>
                        </td>
                    </tr>

                    <tr>
                        <td class="columnTitle_mustbe">单价：</td>
                        <td class="tableContent"><input type="text" name="price"/></td>
                        <td class="columnTitle_mustbe">排序号：</td>
                        <td class="tableContent"><input type="text" name="orderNo"/></td>
                    </tr>

                    <tr>
                        <td class="columnTitle_mustbe">货物描述：</td>
                        <td class="tableContent"><textarea style="width: 120px;" name="productDesc"></textarea>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>

                        <li id="view"><a href="#" onclick="formSubmit('toview.action','_self');this.blur();">查看</a></li>
                        <li id="new"><a href="#" onclick="formSubmit('tocreate.action','_self');this.blur();">新增</a></li>
                        <li id="update"><a href="#" onclick="formSubmit('toupdate.action','_self');this.blur();">修改</a></li>
                        <li id="delete"><a href="#" onclick="formSubmit('delete.action','_self');this.blur();">删除</a></li>
                        <li id="new"><a href="#" onclick="formSubmit('submit.action','_self');this.blur();">上报</a></li>
                        <li id="new"><a href="#" onclick="formSubmit('cancel.action','_self');this.blur();">取消</a></li>

                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!-- 页面主体部分（列表等） -->
    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    货物列表
                </div>
            </div>
        </div>

        <div>

            <div class="eXtremeTable">
                <table id="ec_table" class="tableRegion" width="98%">
                    <thead>
                    <tr>
                        <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                        <td class="tableHeader">序号</td>
                        <td class="tableHeader">厂家名称</td>
                        <td class="tableHeader">货号</td>
                        <td class="tableHeader">数量</td>
                        <td class="tableHeader">包装单位</td>
                        <td class="tableHeader">装率</td>
                        <td class="tableHeader">箱数</td>
                        <td class="tableHeader">单价</td>
                        <td class="tableHeader">总金额</td>
                    </tr>
                    </thead>
                    <tbody class="tableBody">

                    <c:forEach items="${dataList}" var="o" varStatus="status">
                        <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                            <td><input type="checkbox" name="id" value="${o.id}"/></td>
                            <td>${status.index+1}</td>
                            <td>${o.factoryName}</td>
                            <td>${o.productNo}</td>
                            <td>${o.cnumber}</td>
                            <td>${o.packingUnit}</td>
                            <td>${o.loadingRate}</td>
                            <td>${o.boxNum}</td>
                            <td>${o.price}</td>
                            <td>${o.amount}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>

        </div>

    </div>

</form>
</body>
</html>

