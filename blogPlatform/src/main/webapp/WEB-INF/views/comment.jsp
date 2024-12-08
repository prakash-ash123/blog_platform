<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${post.title}</title>
    <style>
        /* General Page Styling */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f9;
            color: #333;
        }

        h2 {
            color: #444;
            border-bottom: 2px solid #ddd;
            padding-bottom: 10px;
        }

        h3 {
            color: #555;
            margin-top: 30px;
        }

        p {
            font-size: 1.1em;
            line-height: 1.6;
            color: #666;
        }

        /* Comments Section */
        ul {
            list-style-type: none;
            padding: 0;
            margin-top: 20px;
        }

        li {
            background: #ffffff;
            margin: 10px 0;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        i {
            color: #999;
            font-size: 0.9em;
        }

        /* Form Styling */
        form {
            margin-top: 20px;
            background: #f9f9f9;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        textarea {
            width: 100%;
            height: 100px;
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
            resize: none;
            margin-bottom: 10px;
        }

        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1em;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* Back to Home Link */
        a {
            display: inline-block;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>${post.title}</h2>
    <p>${post.content}</p>

    <!-- Display comments -->
    <h3>Comments</h3>
    <ul>
        <c:forEach items="${post.comments}" var="comment">
            <li>
                ${comment.content} - <i>${comment.createdAt}</i> by ${comment.user.username}
            </li>
        </c:forEach>
    </ul>

    <!-- Display comment form -->
    <c:choose>
        <c:when test="${not empty sessionScope.loggedInUser}">
            <form action="/post/comments/${post.postId}?userId=${loggedInUser.id}" method="post" enctype="application/json">
                <textarea name="content" placeholder="Write a comment..." required></textarea>
                <button type="submit">Submit</button>
            </form>
        </c:when>
        <c:otherwise>
            <p><a href="/login">Log in</a> to add a comment.</p>
        </c:otherwise>
    </c:choose>

    <a href="/">Back to Home</a>
</body>

</html>
