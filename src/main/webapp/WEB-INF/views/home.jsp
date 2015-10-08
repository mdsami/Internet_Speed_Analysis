<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>Home</title>
<script type="text/javascript"
	src="/ISAnalyser/resources/libs/jquery/jquery-1.11.3.min.js">
	
</script>
<script type="text/javascript"
	src="/ISAnalyser/resources/libs/jqplot/jquery.jqplot.min.js"></script>
<link href="/ISAnalyser/resources/libs/jqplot/jquery.jqplot.min.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
.brdb {
	border-bottom: 1px solid #e9e9e9;
}

.PA5 {
	padding: 5px;
}

.FL {
	float: left;
}

.PR15 {
	padding-right: 15px;
}

.PL15 {
	padding-left: 15px;
}

.brdr {
	border-right: 1px solid #d5d5d5;
}

.PT3 {
	padding-top: 3px;
}

.PB3 {
	padding-bottom: 3px;
}

.UC {
	text-transform: uppercase;
}

.gL_10 {
	color: #7f7f7f;
	font: 38px arial;
}

.gD_12 {
	color: #449703;
	font: 38px arial;
	text-decoration: none;
	font-weight: 700;
}
</style>
</head>
<body>
	<h1>Internet Download Speed Analysis</h1>
	<div class="PA5">
		<strong>The time on the server is ${serverTime}.</strong>
	</div>
	<div class="PA5">
		<strong>Internet Download Speed Analysis</strong>
	</div>
	<div class="PA5">
		<div class="FL PR15 PL15 brdr">
			<div class="PT3 PB3 UC gL_10">Max Speed</div>
			<div class="gD_12 PB3" id="b_prevclose">
				<strong>${responseData.maxSpeed} (kbps)</strong>
			</div>
		</div>
		<div class="FL PR15 PL15 brdr">
			<div class="PT3 PB3 UC gL_10">Min Speed</div>
			<div class="gD_12 PB3" id="b_open">
				<strong>${responseData.minSpeed} (kbps)</strong>
			</div>
		</div>
		<div class="FL PR15 PL15 brdr">
			<div class="PT3 PB3 UC gL_10">Avg Speed</div>
			<div class="gD_12 PB3" id="b_bidprice_qty">
				<strong>${responseData.avgSpeed} (kbps)</strong>
			</div>
		</div>
		<div class="CL"></div>
	</div>

</body>
</html>
