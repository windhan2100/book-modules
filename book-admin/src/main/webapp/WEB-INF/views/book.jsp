<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/base.jsp" %>
<html>
<head>
	<%@ include file="common/header.jsp" %>
</head>
<body >
 <%@ include file="common/nav.jsp" %>

    <div class="container-fluid">
      <div class="row">
	  	<%@ include file="common/nav_sidebar.jsp" %> 
	 	
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">${cityName }客户预约:</h2>
          <%--添加查询开始 --%>
          	<form action="/book" id="query_select" method="post">
			<table id="condtion">
				<tr height="30px">
					<td align="right" width="100px">选择分店：</td>
					<td style="background-color: #FFFFFF">
						<select required="required" id="param_shop" name="param_shop">
							<option value="0">--选择分店--</option>
							<c:if test="${shop != null &&  fn:length(shop) > 0}">
								<c:forEach items="${shop }" var="item" varStatus="status">
									<option value="${item.id }" <c:if test="${item.id == shopId }">selected="selected"</c:if> >${item.name }</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
					<td align="right" width="100px">预约时间：</td>
					<td style="background-color: #FFFFFF">
						<input type="text" style="width: 200px;" id="datepicker" name="param_book_time" placeholder="预约时间" value="${bookTime }"/>
					</td>
					<td align="right" width="100px">预约手机：</td>
					<td style="background-color: #FFFFFF">
						<input type="number" style="width: 200px;" name="param_phone" placeholder="无须选择时间和分店" value="${phone }"/>
					</td>
					<td >
						&nbsp;&nbsp;&nbsp;
						<input type="submit" value="查询" style="width: 80px;height: 26px;background-color: #98FB98;border: 1px solid #000000"/>
					</td>
				</tr>
			</table>
			</form>
			<%-- 添加查询结束 --%> 
          <div class="table-responsive">
            <form action="/book/addorupdate" method="post" id="modify_item_form" style="display: none;">
            	<input type="hidden" id="hd_id" />
				<table id="modify_item_table" class="table table-striped">
					<thead>
		                <tr>
							<th colspan="2" id="form_title">添加新预约：</th>
		                </tr>
		            </thead>
		            <tbody>
						<tr height="30px">
							<td align="right" width="150px">客户姓名：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" style="width: 200px;" name="username" placeholder="请输入客户姓名"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">手机号码：</td>
							<td style="background-color: #FFFFFF">
								<input type="number" style="width: 200px;" name="phone" required="required" placeholder="请输入客户手机号"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">选择分店：</td>
							<td style="background-color: #FFFFFF">
								<select required="required" name="shop" style="width: 200px;">
									<option value="0">--选择分店--</option>
									<c:if test="${shop != null &&  fn:length(shop) > 0}">
										<c:forEach items="${shop }" var="item" varStatus="status">
											<option value="${item.id }">${item.name }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">预约时间：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" id="datetimepicker" style="width: 200px;" name="bookTime" required="required" placeholder="预约时间"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">套餐选择：</td>
							<td style="background-color: #FFFFFF">
								<select required="required" name="type" style="width: 200px;">
									<option value="0">--选择套餐--</option>
									<c:if test="${type != null &&  fn:length(type) > 0}">
										<c:forEach items="${type }" var="item" varStatus="status">
											<option value="${item.id }">${item.typeName }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">支付方式：</td>
							<td style="background-color: #FFFFFF">
								<select required="required" name="payType" style="width: 200px;">
									<option value="0">--选择支付方式--</option>
									<c:if test="${payType != null &&  fn:length(payType) > 0}">
										<c:forEach items="${payType }" var="item" varStatus="status">
											<option value="${item.id }">${item.payName }</option>
										</c:forEach>
									</c:if>
								</select>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">团购码：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" style="width: 200px;" name="payCode" placeholder="团购码"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">实际支付：</td>
							<td style="background-color: #FFFFFF">
								<input type="number" style="width: 200px;" name="payPrice" placeholder="实际支付金额"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right">订单状态：</td>
							<td style="background-color: #FFFFFF">
								<span id="id_coop_platform">
									<input type="radio" name="book_status" value="1"/>预约&nbsp;
									<input type="radio" name="book_status" value="2"/>取消&nbsp;
									<input type="radio" name="book_status" value="0"/>完成&nbsp;
								</span>
							</td>
					    </tr>
						<tr height="30px">
							<td align="right" width="150px">备注信息：</td>
							<td style="background-color: #FFFFFF">
								<textarea cols="50" name="backUp" rows="3" placeholder="备注信息"></textarea>
							</td>
						</tr>
						<tr height="32px">
							<td align="right"></td>
							<td style="background-color: #FFFFFF">
								<input id="book_add_submit" type="button" value="提交"/>
								<input id="book_back" type="button" value="返回"/>
							</td>
						</tr>

		            </tbody>
				</table>
			</form>
			<%-- 查询结果列表 开始 --%>
			<table id="record_list_table" class="table table-striped">
				<thead>
					<tr>
						<td colspan="12">
	                  		<button name="add_book" style="width: 120px;height: 28px;background-color: #98FB98;border: 1px solid #000000">添加新预约</button>
	                  	</td>
					</tr>
	                <tr>
						<th>编号</th>
	                  	<th>姓名</th>
	                  	<th>手机号</th>
	                  	<th>预约时间</th>
	                  	<th>分店</th>
	                  	<th>套餐</th>
	                  	<th width="8%">实际支付</th>
	                  	<th width="8%">支付方式</th>
	                  	<th width="10%">团购码</th>
	                  	<th width="5%">状态</th>
	                  	<%--
	                  	<th>处理时间</th>
	                  	<th>管理员</th>
	                  	 --%>
	                  	<th width="12%">备注</th>
	                  	<th width="8%">操作</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:choose>
	            		<c:when test="${fn:length(records) > 0 }">
	            			<c:forEach items="${records }" var="item" varStatus="status">
								<tr id="tr_${item.id }">
									<td>${status.count }</td>
									<td>${item.username }</td>
									<td>${item.phone }</td>
									<td style="color:blue;font-weight:bold;"><fmt:formatDate value="${item.bookTime }" pattern="MM-dd HH:mm"/></td>
									<td>${item.shopName }</td>
									<td>${item.typeName }</td>
									<td>${item.payPrice }</td>
									<td>${item.payName }</td>
									<td>${item.payCode }</td>
									<c:choose>
										<c:when test="${item.status == 1 }">
											<td>预约</td>
										</c:when>
										<c:when test="${item.status == 2 }">
											<td style="color: #FF2D2D;">取消</td>
										</c:when>
										<c:when test="${item.status == 0 }">
											<td style="color: #00DB00;">完成</td>
										</c:when>
									</c:choose>
									<%--
									<td><fmt:formatDate value="${item.updateTime }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									<td>${item.updateUser }</td>
									 --%>
									<td width="200px">${item.backUp }</td>
									<td>
										<c:if test="${isAdmin == 1 }">
											<input cmd="delete_book" type="button" name="${item.id }" value="删除"/>
										</c:if>
										<input cmd="update_book" type="button" name="${item.id }" value="修改"/>
									</td>
								</tr>
							</c:forEach>
	            		</c:when>
	            		<c:otherwise>
	            			<tr>
								<td colspan="13">暂时还没有预约...</td>
			                </tr>
	            		</c:otherwise>
	            	</c:choose>
				</tbody>
			</table>
			<%-- 查询结果列表 开始 --%>
          </div>
        </div>
      </div>
    </div>
    
     <c:catch var="exception">
	  	<%@ include file="common/footer.jsp" %>
	 </c:catch>
    <script src="../../static/script/admin.js"></script>
    <link rel="stylesheet" type="text/css" href="../../static/script/datetimepicker/datetimepicker.css"/>
    <script src="../../static/script/datetimepicker/jquery.datetimepicker.full.min.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		//$("#param_shop").val('${shopId}');
    		$.datetimepicker.setLocale('ch');
			var maxDate = new Date();
			maxDate.setMonth(maxDate.getMonth()+2);
    		
			/**
			 * [日期选择]
			 */
    		$('#datepicker').datetimepicker({
    			timepicker:false,
				format:'Y-m-d',
				maxDate:maxDate
    		});
			/**
			 * [时间和日期选择,初始化]
			 */
			$('#datetimepicker').datetimepicker({
				format:'Y-m-d H:i',
				minDate:new Date(),
				allowTimes:[
				            '09:00', '09:15', '09:30', '09:45',
				            '10:00', '10:15', '10:30', '10:45',
				            '11:00', '11:15', '11:30', '11:45',
				            '12:00', '12:30', 
				            '13:00', '13:15', '13:30', '13:45',
				            '14:00', '14:15', '14:30', '14:45',
				            '15:00', '15:15', '15:30', '15:45',
				            '16:00', '16:15', '16:30', '16:45',
				            '17:00', '17:15', '17:30', '17:45',
				            '18:00', '18:15', '18:30', '18:45',
				            '19:00', '19:15', '19:30', '19:45'
				           ],
				maxDate:maxDate
			});
    	});
    </script>
    
</body>
