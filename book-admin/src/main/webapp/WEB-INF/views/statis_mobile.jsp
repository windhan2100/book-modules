<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/base.jsp" %>
<html>
<head>
	<%@ include file="common/header.jsp" %>
	<style type="text/css"> 
   		label {
   			font-weight:normal;
   		}
   	</style>
</head>
<body >
 <%@ include file="common/nav_mobile.jsp" %>

    <div class="container-fluid">
      <div class="row">
	  	<%@ include file="common/nav_sidebar.jsp" %> 
	 	
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">${cityName }订单统计:</h2>
          <%--添加查询开始 --%>
          	<form action="/order/statistics/beijing" id="query_select" method="post">
			<table id="condtion">
				<tr>
					<td colspan="2" style="color:red;">由于手机屏幕较小,建议使用电脑版!</td>
				</tr>
				<tr height="50px">
					<td align="right" width="50px">选择分店：</td>
					<td style="background-color: #FFFFFF;width:400px;">
						<c:forEach items="${shop }" var="item" varStatus="status">
							<label>
								<input type="checkbox" name="shopId" value="${item.id }" 
									<c:if test="${shopIds != null && '' != shopIds}">
										<c:forEach items="${shopIds }" var="i">
											<c:if test="${i == item.id }">
												checked="checked"
											</c:if>
										</c:forEach>
									</c:if>
								/>${item.name }&nbsp;&nbsp;
							</label>
						</c:forEach>
					</td>
				</tr>
				<tr height="50px">
					<td align="right" width="50px">选择套餐：</td>
					<td style="background-color: #FFFFFF">
						<c:forEach items="${type }" var="item" varStatus="status">
							<label>
								<input type="checkbox" name="typeId" value="${item.id }"
									<c:if test="${typeIds != null && '' != typeIds}">
										<c:forEach items="${typeIds }" var="i">
											<c:if test="${i == item.id }">
												checked="checked"
											</c:if>
										</c:forEach>
									</c:if>
								/>${item.typeName }&nbsp;&nbsp;
							</label>
						</c:forEach>
					</td>
				</tr>
				<tr height="70px">
					<td align="right" width="50px">支付方式：</td>
					<td style="background-color: #FFFFFF">
						<c:forEach items="${payType }" var="item" varStatus="status">
							<label>
								<input type="checkbox" name="payId" value="${item.id }" 
									<c:if test="${payIds != null && '' != payIds}">
										<c:forEach items="${payIds }" var="i">
											<c:if test="${i == item.id }">
												checked="checked"
											</c:if>
										</c:forEach>
									</c:if>
								/>${item.payName }&nbsp;&nbsp;
							</label>
						</c:forEach>
					</td>
				</tr>
				<tr height="50px">
					<td align="right" width="50px">订单状态：</td>
					<td style="background-color: #FFFFFF">
						<c:forEach items="${status }" var="item" varStatus="status">
							<label>
								<input type="checkbox" name="statusId" value="${item.id }" 
									<c:if test="${statusIds != null && '' != statusIds}">
										<c:forEach items="${statusIds }" var="i">
											<c:if test="${i == item.id }">
												checked="checked"
											</c:if>
										</c:forEach>
									</c:if>
								/>${item.statusName }&nbsp;&nbsp;
							</label>
						</c:forEach>
					</td>
				</tr>
				<tr height="50px">
					<td align="right" width="100px">开始日期：</td>
					<td style="background-color: #FFFFFF">
						<input type="text" style="width: 150px;" readonly="readonly" id="startDate" name="startDate" placeholder="开始日期" value="${startDate }"/>
					</td>
				</tr>
				<tr height="50px">
					<td align="right" width="100px">结束日期：</td>
					<td style="background-color: #FFFFFF">
						<input type="text" style="width: 150px;" readonly="readonly" id="endDate" name="endDate" placeholder="结束日期" value="${endDate }"/>
					</td>
			   </tr>
			   <tr height="50px">
			   		<input type="hidden" id="pageNo" name="pageNo" value="${page.currPage }"/>
			   		<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"/>
					<td >
						&nbsp;&nbsp;&nbsp;
						<input type="button" id="query_btn" value="查询" style="width: 80px;height: 26px;background-color: #98FB98;border: 1px solid #000000"/>
					</td>
					<td >
						&nbsp;&nbsp;&nbsp;
						<input type="button" id="query_reset" value="重置" style="width: 80px;height: 26px;background-color: #98FB98;border: 1px solid #000000"/>
					</td>
				</tr>
			</table>
			</form>
			<%-- 添加查询结束 --%> 
          <div class="table-responsive">
            <form action="/book/addorupdate" method="post" id="modify_item_form" style="display: none;">
            	<input type="hidden" id="hd_id" />
				<table id="modify_item_table" class="table table-striped">
					<%--
					
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
 --%>
		            </tbody>
				</table>
			</form>
			<%-- 查询结果列表 开始 --%>
			<table id="record_list_table" class="table table-striped">
				<thead>
					<tr>
						<td colspan="12">
							        每页
							   <select name="changePageSize">
									<c:forEach items="${pageSizeList }" var="i">
										<option value="${i }" <c:if test="${i == page.pageSize }">selected="selected"</c:if> >${i }</option>
									</c:forEach>
							   </select>
							         条 &nbsp;&nbsp;&nbsp;&nbsp;
							  [共<b>${page.totalCount }</b>条记录|
							       共<b>${page.totalPages }</b>页]&nbsp;&nbsp;&nbsp;&nbsp;
							       实际支付总额&yen:<b style="color:red;">${totalPrice } 元</b>&nbsp;&nbsp;&nbsp;&nbsp;
							      跳转到第
							  <select name="changePageNo">
									<c:forEach begin="1" end="${page.totalPages }" var="i">
										<option value="${i }" <c:if test="${i == page.currPage }">selected="selected"</c:if> >${i }</option>
									</c:forEach>
							  </select>页 
	                  	</td>
						<td>
							<%--
	                  		<button  name="statistics" style="width: 120px;height: 28px;background-color: #98FB98;border: 1px solid #000000">查看统计</button>
							 --%>
	                  	</td>
					</tr>
	                <tr>
						<th>编号</th>
	                  	<th>姓名</th>
	                  	<th width="8%">手机号</th>
	                  	<th width="11%">预约时间</th>
	                  	<th width="7%">分店</th>
	                  	<th width="6%">套餐</th>
	                  	<th width="7%">实际支付</th>
	                  	<th width="7%">支付方式</th>
	                  	<th width="8%">团购码</th>
	                  	<th width="5%">状态</th>
	                  	<th width="12%">更新时间</th>
	                  	<th width="8%">管理员</th>
	                  	<th width="9%">备注</th>
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
									<td style="color:blue;font-weight:bold;"><fmt:formatDate value="${item.bookTime }" pattern="yyyy-MM-dd HH:mm"/></td>
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
									<td><fmt:formatDate value="${item.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${item.updateUser }</td>
									<td>${item.backUp }</td>
								</tr>
							</c:forEach>
	            		</c:when>
	            		<c:otherwise>
	            			<tr>
								<td colspan="13">该条件下没有查询到订单...</td>
			                </tr>
	            		</c:otherwise>
	            	</c:choose>
            		<tr>
						<td colspan="12">
							        每页
							   <select name="changePageSize">
									<c:forEach items="${pageSizeList }" var="i">
										<option value="${i }" <c:if test="${i == page.pageSize }">selected="selected"</c:if> >${i }</option>
									</c:forEach>
							   </select>
							         条 &nbsp;&nbsp;&nbsp;&nbsp;
							  [共<b>${page.totalCount }</b>条记录 |
							       共<b>${page.totalPages }</b>页]&nbsp;&nbsp;&nbsp;&nbsp;
							       实际支付总额&yen:<b style="color:red;">${totalPrice } 元</b>&nbsp;&nbsp;&nbsp;&nbsp;
							      跳转到第
							  <select name="changePageNo">
									<c:forEach begin="1" end="${page.totalPages }" var="i">
										<option value="${i }" <c:if test="${i == page.currPage }">selected="selected"</c:if> >${i }</option>
									</c:forEach>
							  </select>页 
	                  	</td>
						<td>
							<%--
	                  		<button name="statistics" style="width: 120px;height: 28px;background-color: #98FB98;border: 1px solid #000000">查看统计</button>
							 --%>
	                  	</td>
					</tr>
				</tbody>
			</table>
			<%-- 查询结果列表 结束 --%>
          </div>
        </div>
      </div>
    </div>
    
     <c:catch var="exception">
	  	<%@ include file="common/footer.jsp" %>
	 </c:catch>
    <script src="../../static/script/admin.js"></script>
    <link rel="stylesheet" type="text/css" href="../../static/script/LCalendar/LCalendar.css"/>
    <script src="../../static/script/LCalendar/LCalendar.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    	    var calendarStart = new LCalendar();
    	    calendarStart.init({
    	        'trigger': '#startDate', //标签id
    	        'type': 'date', //date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择,
    	        'minDate': '2017-09-01'//, //最小日期
    	       // 'maxDate': addMonth(3)
    	    });
    		
    	    var calendarEnd = new LCalendar();
    	    calendarEnd.init({
    	        'trigger': '#endDate', //标签id
    	        'type': 'date', //date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择,
    	        'minDate': '2017-09-01'//, //最小日期
    	       // 'maxDate': addMonth(3)
    	    });
    	});
    </script>
    
</body>
