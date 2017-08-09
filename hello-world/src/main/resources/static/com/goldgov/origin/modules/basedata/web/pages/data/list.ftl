<!DOCTYPE">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<@res tag="link" src="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
</head>
<body>

	<form action="" method="post"></form>
	<div style="margin: 5px;">
		<div>
			<div class="btn-group pull-right" role="group" aria-label="...">
				<button type="button" class="btn btn-default new-btn" onclick="window.open('./preAddData?categoryCode=${RequestParameters['categoryCode']}&parentData.dataID=${RequestParameters['parentData.dataID']!''}','_self')">新增</button>
				<button type="button" class="btn btn-default edit-btn">修改</button>
				<button type="button" class="btn btn-danger del-btn">删除</button>
			</div>
		</div>
		<table class="table table-bordered table-condensed table-head"
			style="margin-top: 5px; clear: both">
			<thead>
				<tr>
					<th>&nbsp;</th>
					<th>数据名</th>
					<th>数据值</th>
					<th>描述</th>
					<th>子数据</th>
				</tr>
			</thead>
			<tbody>
				<#list listData as data> <#escape data as data?html>
				<tr>
					<td style="text-align: center;"><input type="checkbox"
						name="ids" value="${data.dataID}" /></td>
					<td>${data.dataName}</td>
					<td>${data.dataValue}</td>
					<td>${data.description!}</td>
					<td><a href="./listData?dataID=${data.dataID}&categoryCode=${RequestParameters['categoryCode']}&parentData.dataID=${data.dataID}">【进入】</a></td>
				</tr>
				</#escape> </#list>
			</tbody>
		</table>
	</div>
	${csrfToken(true)} ${webToken(true)}
	<input type="hidden" name="parentData.dataID" value="${RequestParameters['parentData.dataID']!''}";>
	</form>
</body>
<@res tag="script" src="/webjars/jquery/3.2.1/jquery.min.js"/>
</html>