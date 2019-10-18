<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
        <%@include file="meta-data.jsp" %>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <form action="signup" method="POST" class="col s8 card offset-s2">
                    <div class="input-field center">
                        <h2 style="display:inline;">Signup</h2><h5 style="display:inline;">&nbsp;OR&nbsp;</h5><a href="signin"><h3 style="display:inline;">SignIn</h3></a> 
                    </div>
                    <div>
                        <label for="name">Name : </label>
                        <input type="text" name="name" id="name" required>
                    </div>
                    <div>
                        <label for="number">Mobile Number : </label>
                        <input type="number" name="number" id="number" required>
                    </div>
                    <div>
                        <label for="email">Email:</label>
                        <input type="email" name="email" id="email" required>
                    </div>
                    <div>
                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password" required>
                    </div>
                    <div class="input-field">
                        <button class="btn waves-light waves-effect" type="submit">Sign up</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
