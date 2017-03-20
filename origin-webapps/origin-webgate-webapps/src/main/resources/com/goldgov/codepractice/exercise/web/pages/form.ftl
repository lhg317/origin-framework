<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<#if (!exercise??) || (!exercise.exerciseID??) >
<form method="post" action="./add">
</#if>
<#if exercise?? && (exercise.exerciseID??)>
<form method="post" action="./update">
<input type="hidden" name="exerciseID" value="${(exercise.exerciseID)!}">
</#if>

<@i18n code="label.title" suffix="colon"/><input type="text" name="title" value="${(exercise.title)!}"><br>
<@i18n code="label.category" suffix="colon"/>
<select name="category">
	<option value="1" <#if (exercise.category)?? && exercise.category == 1 >selected</#if> >Advanced Math</option>
	<option value="2" <#if (exercise.category)?? && exercise.category == 2 >selected</#if>>Brute Force</option>
	<option value="3" <#if (exercise.category)?? && exercise.category == 3 >selected</#if>>Dynamic Programming</option>
	<option value="4" <#if (exercise.category)?? && exercise.category == 4 >selected</#if>>Encryption/Compression</option>
	<option value="5" <#if (exercise.category)?? && exercise.category == 5 >selected</#if>>Geometry</option>
	<option value="6" <#if (exercise.category)?? && exercise.category == 6 >selected</#if>>Graph Theory</option>
	<option value="7" <#if (exercise.category)?? && exercise.category == 7 >selected</#if>>Greedy</option>
	<option value="8" <#if (exercise.category)?? && exercise.category == 8 >selected</#if>>Math</option>
	<option value="9" <#if (exercise.category)?? && exercise.category == 9 >selected</#if>>Recursion</option>
	<option value="10" <#if (exercise.category)?? && exercise.category == 10 >selected</#if>>Search</option>
	<option value="11" <#if (exercise.category)?? && exercise.category == 11 >selected</#if>>Simple Math</option>
	<option value="12" <#if (exercise.category)?? && exercise.category == 12 >selected</#if>>Simple Search/Iteration</option>
	<option value="13" <#if (exercise.category)?? && exercise.category == 13 >selected</#if>>Simulation</option>
	<option value="14" <#if (exercise.category)?? && exercise.category == 14 >selected</#if>>Sorting</option>
	<option value="15" <#if (exercise.category)?? && exercise.category == 15 >selected</#if>>String Manipulation</option>
	<option value="16" <#if (exercise.category)?? && exercise.category == 16 >selected</#if>>String Parsing</option>
</select><br>
<@i18n code="label.difficulty" suffix="colon"/>
<select name="difficulty">
	<option value="1" <#if (exercise.difficulty)?? && exercise.difficulty == 1 >selected</#if>>简单</option>
	<option value="2" <#if (exercise.difficulty)?? && exercise.difficulty == 2 >selected</#if>>一般</option>
	<option value="3" <#if (exercise.difficulty)?? && exercise.difficulty == 3 >selected</#if>>困难</option>
</select>
<br>
<@i18n code="label.timeLimit" suffix="colon"/><input type="text" name="timeLimit" value="${(exercise.timeLimit)!}">分钟<br>
<@i18n code="label.scorePoint" suffix="colon"/><input type="text" name="scorePoint" value="${(exercise.scorePoint)!}"><br>
<@i18n code="label.statement" suffix="colon"/><textarea name="statement">${(exercise.statement)!}</textarea><br>
<@i18n code="label.className" suffix="colon"/><input type="text" name="definitionClass" value="${(exercise.definitionClass)!}"><br>
<@i18n code="label.methodName" suffix="colon"/><input type="text" name="definitionMethod" value="${(exercise.definitionMethod)!}"><br>
<@i18n code="label.parameter" suffix="colon"/><input type="text" name="definitionParams" value="${(exercise.definitionParams)!}"><br>
<@i18n code="label.return" suffix="colon"/><input type="text" name="definitionReturn" value="${(exercise.definitionReturn)!}"><br>
<@i18n code="label.constraint" suffix="colon"/><input type="text" name="constraints" value="${(exercise.constraints)!}"><br>
<@i18n code="label.note" suffix="colon"/><textarea name="note">${(exercise.note)!}</textarea><br>
<@i18n code="label.example" suffix="colon"/><textarea name="example" >${(exercise.example)!}</textarea><br>
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>
${webToken(true)}
<br>
<input type="submit" value="<@i18n code="submit"/>"/> <input type="button" value="<@i18n code="back"/>" onclick="window.open('./findList','_self')"/>

</form>

</body>
</html>