<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/base.jsp" %>
<html>
<head>
	<%@ include file="common/header.jsp" %>
</head>
<body >
 <%@ include file="common/nav_mobile.jsp" %>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">${cityName }客户预约:</h2>
          <%--添加查询开始 --%>
          	<form action="/book" id="query_select" method="post">
			<table id="condtion">
				<tr style="magin:10px;height:50px;">
					<td align="right" width="100px">预约时间：</td>
					<td style="background-color: #FFFFFF">
						<input type="text" style="width: 200px;" id="datepicker" readonly="readonly" name="param_book_time" placeholder="预约时间" value="${bookTime }"/>
					</td>
				</tr>
				<tr style="magin:10px;height:50px;">
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
				</tr>
				<tr style="magin:10px;height:50px;">
					<td align="right" width="100px">预约手机：</td>
					<td style="background-color: #FFFFFF">
						<input type="number" style="width: 200px;" name="param_phone" placeholder="无须选择时间和分店" value="${phone }"/>
					</td>
				</tr>
				<tr style="magin:10px;height:50px;">
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
		            	<%--
						<tr height="30px">
							<td align="right" width="150px">客户姓名：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" style="width: 200px;" name="username" placeholder="请输入客户姓名"/>
							</td>
						</tr>
		            	 --%>
						<tr height="30px">
							<td align="right" width="150px">手机号码：</td>
							<td style="background-color: #FFFFFF">
								<input type="number" style="width: 200px;" name="phone" required="required" placeholder="请输入客户手机号"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">预约时间：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" id="datetimepicker" readonly="readonly" style="width: 200px;" name="bookTime" required="required" placeholder="预约时间"/>
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
						<%--
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
						</tr> --%>
						<tr height="30px">
							<td align="right" width="150px">备注信息：</td>
							<td style="background-color: #FFFFFF">
								<textarea cols="20" name="backUp" rows="3" placeholder="备注信息"></textarea>
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
						<%--
	                  	<th>姓名</th>
						 --%>
	                  	<th>手机号</th>
	                  	<th>预约时间</th>
	                  	<th width="10%">状态</th>
	                  	<th>分店</th>
	                  	<%--
	                  	<th>套餐</th>
	                  	<th width="8%">实际支付</th>
	                  	<th width="8%">支付方式</th>
	                  	<th width="10%">团购码</th>
	                  	 --%>
	                  
	                  	
	                  	<%--
	                  	<th>处理时间</th>
	                  	<th>管理员</th>--%>
	                  	<th width="12%">备注</th>
	                  	 
	                  	<th width="10%">操作</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:choose>
	            		<c:when test="${fn:length(records) > 0 }">
	            			<c:forEach items="${records }" var="item" varStatus="status">
								<tr id="tr_${item.id }">
									<td>${status.count }</td>
									<%--
									<td>${item.username }</td>
									 --%>
									<td>${item.phone }</td>
									<td style="color:blue;font-weight:bold;"><fmt:formatDate value="${item.bookTime }" pattern="MM-dd HH:mm"/></td>
									
									<%--
									<td>${item.typeName }</td>
									<td>${item.payPrice }</td>
									<td>${item.payName }</td>
									<td>${item.payCode }</td>
									 --%>
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
									<td>${item.shopName }</td>
									<%--
									<td><fmt:formatDate value="${item.updateTime }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									<td>${item.updateUser }</td> --%>
									<td>${item.backUp }</td>
									
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
    <script src="../../static/script/admin.js"></script>
    <link rel="stylesheet" type="text/css" href="../../static/script/LCalendar/LCalendar.css"/>
    <script src="../../static/script/LCalendar/LCalendar.js"></script>
    <script type="text/javascript">
    
	    var calendar = new LCalendar();
	    calendar.init({
	        'trigger': '#datepicker', //标签id
	        'type': 'date', //date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择,
	        'minDate': '2017-09-01', //最小日期
	        'maxDate': addMonth(3)
	    });
	    var calendardatetime = new LCalendar();
	    calendardatetime.init({
	        'trigger': '#datetimepicker',
	        'type': 'datetime',
	        'minDate': '2017-09-01', //最小日期
	        'maxDate': addMonth(3)
	    });
    
    	$(document).ready(function(){
    		$("#param_shop").val('${shopId}');
    	});
    </script>
    
</body>
