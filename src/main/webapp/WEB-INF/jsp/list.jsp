<%--
  Created by IntelliJ IDEA.
  User: Evan
  Date: 9/22/2016
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>秒杀列表</title>
  <%@include file="common/head.jsp"%>
  <%@include file="common/tag.jsp"%>
</head>
<body>

<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading text-center">
      <h2>秒杀列表</h2>
    </div>
    <div class="panel-body">
      <table class="table table-hover">
        <thead>
          <tr>
            <th>名称</th>
            <th>库存</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>创建时间</th>
            <th>详情页</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <c:forEach var="sk" items="${list}">
              <td>${sk.name}</td>
              <td>${sk.number}</td>
              <td>
                <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td>
                <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td>
                <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
              </td>
              <td>
                <a class="btn btn-info" href="/seckill/${sk.seckillId}/detail" target="_blank">link</a>
              </td>
            </c:forEach>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js" />
</html>
