<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css">         
        <script src="../js/bootstrap.min.js"></script>       
    </head>
    
    <body>          
        <div class="container">

            <h2>Philosophy Path</h2>
            <!--Search Form -->
            <form action="/philosophy" method="get" id="seachPhilosophyForm" role="form">
                <input type="hidden" id="searchAction" name="searchAction" value="searchByArticle">

                <!-- Input -->
                <div class="form-group col-xs-5">
                    <input type="text" name="startArticle" id="startArticle" class="form-control" required="true" placeholder="Wikipedia page to get to Philosophy"/>                    
                </div>
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> 
                </button>
                <br></br>
            </form>

            <!--Philosophy List-->
            <c:if test="${(not empty pathList) and (pathList[fn:length(pathList) - 1] != 'LOOP')}">
				<div class="alert alert-success">
 					 <strong>Success!</strong> We were able to find the path to Philosophy.
				</div>
            </c:if> 

            <c:if test="${pathList[fn:length(pathList) - 1] == 'LOOP'}">
                <div class="alert alert-warning">
                    <strong>Loop!</strong> Seems like we are going around in a cycle.
                </div>
            </c:if> 

            <c:if test="${empty pathList}">                
                <div class="alert alert-danger">
                     <strong>Unlucky.</strong> It seems like there is something wrong with the links
                </div>
            </c:if> 

            <form action="/philosophy" method="post" id="philosophyForm" role="form" >              
                <input type="hidden" id="idPhilosophy" name="idPhilosophy">
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty pathList}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td>Articles:</td>
                                </tr>
                            </thead>
                            
                            <c:forEach var="article" items="${pathList}">
                                <tr>     
                                    <td>&emsp;${article}</td>                          
                                </tr>
                            </c:forEach>
                            <tr>
                            	<td>Number of hops: <strong>${fn:length(pathList)}</strong></td>       
                        </table>  
                    </c:when>                    
                </c:choose>                        
            </form>
        </div>
    </body>
</html>