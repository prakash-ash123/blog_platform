<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create New Post</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>


<style>
        /* General styling */
               /* Login message styling */
        .login-message {
            text-align: center;
            background-color: #ffcccc;
            color: #d8000c;
            border: 1px solid #d8000c;
            padding: 20px;
            margin: 20px auto;
            max-width: 600px;
            border-radius: 5px;
            font-size: 18px;
        }

        .login-message a {
            color: #0056b3;
            text-decoration: none;
            font-weight: bold;
        }

        .login-message a:hover {
            text-decoration: underline;
        }
    </style>

<body>
    <h1>Create a New Post</h1>

    <!-- Check if user is logged in -->
    <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
            <!-- Display form for creating a post -->
            <form action="/posts" method="post">
                <input type="text" name="title" placeholder="Title" required />
                <textarea name="content" placeholder="Content" required></textarea>
                <button type="submit">Publish</button>
            </form>
        </c:when>
        <c:otherwise>
            <!-- Prompt user to log in -->
			<div class="login-message">
			        <p>You need to <a href="/login">log in</a> to create a post.</p>
			    </div>
        </c:otherwise>
    </c:choose>

    <a href="/">Back to Home</a>
</body>
</html>
