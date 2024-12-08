<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Blog Platform</title>
	<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
	
	<div style = "display:block;"  class="button-container">
	    <c:choose>
	        <c:when test="${not empty loggedInUser}">
	            <span>
	                <img class="profile-picture-large" 
	                     src="${loggedInUser.profilePictureUrl != null ? loggedInUser.profilePictureUrl : '/images/default-profile.png'}" 
	                     alt="${loggedInUser.username}" />
	                Welcome, <strong>${loggedInUser.username}</strong>!
	            </span>
	            <a href="/logout" class="button">Logout</a>
	        </c:when>
	        <c:otherwise>
	            <a href="/login" class="button">Login</a>
	            <a href="/register" class="button">Register</a>
	        </c:otherwise>
	    </c:choose>
	</div>

	
    <h1>Blog Posts</h1>

    <!-- Link to create a new post only visible if logged in -->
    <c:if test="${not empty loggedInUser}">
        
    </c:if>
	<a href="/new-post" class="button">Create New Post</a>
	<hr>
    <ul>
        <c:forEach items="${posts}" var="post">
            <li>
                
				<div class="user-details">
				    <img class="profile-picture-small" 
				         src="${post.user.profilePictureUrl != null ? post.user.profilePictureUrl : '/images/default-profile.png'}" 
				         alt="${post.user.username}" />
				    <span>
				        <strong>${post.user.username}</strong> 
				        (<a href="mailto:${post.user.email}">${post.user.email}</a>)
						
				    </span>
				</div>
				
				</div>
					<h2>${post.title}</h2>
				    <p>${post.content}</p>
						
					         
					               <div>
					                   <form method="post" action="/reaction/like/${post.postId}?userId=${loggedInUser.id}" style="margin: 0; padding: 0;">
						                       <button style = "margin:0" type="submit" class="reaction-button like" 
					                               <c:if test="${post.user.id == loggedInUser.id}">disabled</c:if>>
						                           Like (${post.likes})
						                       </button>
					                   </form>
					                   <form method="post" action="/reaction/dislike/${post.postId}?userId=${loggedInUser.id}" style="margin: 0; padding: 0;">
					                       <button style = "margin:0" type="submit" class="reaction-button dislike" 
					                               <c:if test="${post.user.id == loggedInUser.id}">disabled</c:if>>
						                           Dislike (${post.dislikes})
					                       </button>
					                   </form>
									   <hr>
					               </div>
								  
                
                <h4>Comments:</h4>
				<ul>
				    <c:forEach items="${post.comments}" var="comment">
				        <li>
							<div class="user-details">
							    <img class="profile-picture-small" 
							         src="${comment.user.profilePictureUrl != null ? comment.user.profilePictureUrl : '/images/default-profile.png'}" 
							         alt="${comment.user.username}'s profile picture" />
									 <strong>${comment.user.username} :</strong>
							    <span>${comment.content} - <i>${comment.createdAt}</i></span>
							</div>

				    </c:forEach>
				</ul>
				<a href="/post/${post.postId}">Add Comment</a>
            </li>
			<hr>
        </c:forEach>
		
    </ul>
</body>
</html>
