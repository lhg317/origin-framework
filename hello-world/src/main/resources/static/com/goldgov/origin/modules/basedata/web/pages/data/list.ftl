<!DOCTYPE">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<@res tag="link" src="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
<@res tag="link" src="/skin/default/css/css.css"/>
</head>
<body>

	<div style="margin: 5px;">
		<div>
			<div class="btn-group pull-right" role="group" aria-label="...">
				<button type="button" class="btn btn-default new-btn">新增</button>
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
				</tr>
				</#escape> </#list>
			</tbody>
		</table>
	</div>
	${csrfToken(true)} ${webToken(true)}
	<br>
	</form>
</body>
<@res tag="script" src="/webjars/jquery/1.9.1/jquery.min.js"/>
<script>
	$(function() {
		var addWin = new WinAgency(document, "基础数据信息", "test-2-1.html", 600, 400);
		//设置回调函数
		addWin.callback = function() {
			this.location.reload();
		}
		$(".new-btn").click(function() {
			addWin.open();
		});
	});
</script>
</html>