<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Ubuntu" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/javascript.util/0.12.12/javascript.util.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/static/css/cvUser.css"/>">
</head>
<body>
    <div class="bg-white">
        <div class="row m-4 pt-5">
            <div class="col-auto">
                <img class="rounded-circle" width="120px" height="120px" src="<c:url value="/static/image/${user.imageUrl}"/>">
            </div>
            <div class="col ml-3">
                <h2 class="font-weight-bold">${user.name}</h2>
                <h4 class="text-primary ">${user.career}</h4>
                <p>${user.maxim}</p>
            </div>
        </div>
        <ul class="midrow nav justify-content-center">
            <li class="nav-item">
                <p class="col-auto ml-4 m-2 text-white"><i class="far fa-envelope"></i> ${user.email}</p>
            </li>
            <li class="nav-item">
                <p class="col ml-3 m-2 text-white"><i class="fas fa-mobile-alt"></i> ${user.phone}</p>
            </li>
            <li class="nav-item">
                <p class="col ml-3 m-2 text-white"><i class="fas fa-map-marker-alt"></i> ${user.address}</p>
            </li>
            <li class="nav-item">
                <p class="col ml-3 m-2 text-white"><i class="far fa-calendar-alt"></i> ${user.birthday}</p>
            </li>
            <li class="nav-item">
                <p class="col-auto m-2 mr-4 text-white"><i class="fab fa-facebook-f"></i> ${user.facebook}</p>
            </li>
        </ul>
        <div class="row m-1 mt-3">
            <div class="col">
                <button type="button" onclick="addSkill(this)" class="btnAddLeft mb-3 btn btn-outline-success">Add</button>
                <button type="button" style="float: right;" class="saveAll mb-3 btn btn-outline-success">Save All</button>
                <c:forEach items="${user.skills}" var="skill">
                <c:if test="${skill.indexDisplay == 1}">
	                <div class="cardSkill mb-3 left">
	                	<div class='form-inline'>
        					<input value="${skill.skillName}" class='skillInput form-control' placeholder='Skill. Ex: Language, Technical, Exp'/>
        					<div class='form-group m-1'>
        						<select class='form-control typeDisplay'>
					        		<option ${skill.typeDisplay == 1 ? 'selected' : ''}>1</option>
					        		<option ${skill.typeDisplay == 2 ? 'selected' : ''}>2</option>
					        		</select>
					        </div>
							<button id='deleteSkill' onclick='deleteSkill(this)' class='m-1 btn btn-danger'>X</button>
    					</div>
	    				<ul>
	    					<c:forEach items="${skill.detailSkills}" var="detailSkill" varStatus="loop">
		        				<li class='form-inline'>
					            	<input id='detailSkill' class='mt-2 form-control mb-2 mr-sm-2' placeholder='Detail of skill' value="${detailSkill.detailSkill}"/>
					            	<button id='deleteDetailSkill' onclick='deleteDetailSkill(this)' class='btn btn-danger'>X</button>
					            	<c:if test="${loop.last}">
					            		<button id='addDetailSkill' onclick='addDetailSkill(this)' class='ml-2 btn btn-success'>+</button>
					            	</c:if>
					        	</li>
							</c:forEach>	        
				    	</ul>
	                </div>
        		</c:if>
                </c:forEach>
            </div>
            <div class="col ml-3">
                <button type="button" onclick="addSkill(this)" class="btnAddRight mb-3 btn btn-outline-success">Add</button>
                <c:forEach items="${user.skills}" var="skill">
                <c:if test="${skill.indexDisplay == 2}">
	                <div class="cardSkill mb-3 right">
	                	<div class='form-inline'>
        					<input value="${skill.skillName}" class='skillInput form-control' placeholder='Skill. Ex: Language, Technical, Exp'/>
        					<div class='form-group m-1'>
        						<select class='form-control typeDisplay'>
        						<c:if test=""></c:if>
					        		<option ${skill.typeDisplay == 1 ? 'selected' : ''}>1</option>
					        		<option ${skill.typeDisplay == 2 ? 'selected' : ''}>2</option>
				        		</select>
					        </div>
					        <button id='deleteSkill' onclick='deleteSkill(this)' class='m-1 btn btn-danger'>X</button>
    					</div>
	    				<ul>
	    					<c:forEach items="${skill.detailSkills}" var="detailSkill" varStatus="loop">
		        				<li class='form-inline'>
					            	<input id='detailSkill' class='mt-2 form-control mb-2 mr-sm-2' placeholder='Detail of skill' value="${detailSkill.detailSkill}"/>
					            	<button id='deleteDetailSkill' onclick='deleteDetailSkill(this)' class='btn btn-danger'>X</button>
				    				<c:if test="${loop.last}">
					            		<button id='addDetailSkill' onclick='addDetailSkill(this)' class='ml-2 btn btn-success'>+</button>
					            	</c:if>
					        	</li>
							</c:forEach>	        
				    	</ul>
	                </div>
        		</c:if>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="popup" aria-live="polite" aria-atomic="true" style="position: relative; min-height: 100px;">
        <div style="position: absolute; top: 50%; right: 50%; transform: translate(40%,-50%); z-index: 2;">
            <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-delay="3000">
                <div class="toast-header">
                    <strong class="mr-auto">Notice</strong>
                    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="toast-body text-success">
                    Saved successfully.
                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value="/static/js/editCV.js"/>"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
</body>
</html>