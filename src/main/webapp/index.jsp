<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

            <div class="alert alert-info">
                <strong>Hello There!</strong> Let's find the path to /Philosophy!
            </div>
        </div>
    </body>
</html>