

<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<b style="margin-left: 40%">TASK MANAGER</b>
<hr />
Task manager app for assigning a tasks of certain priority and status. Designed for  a small IT collective  ( 5 persons , can  be extended to any number of users ).<br/>
Tasks are passed by email ( creation  and due date, status, priority are mentioned in email ).<br/>
All tasks are stored in database , further  modifications are reflected in database<br/>
Tasks are represented  in  a form of table ( can be sorted by different columns ), deleted or marked as completed. Single task can be searched via search window.<br/>
All tasks statistics are represented in the form of graphs and charts - any modifications ( assigning a new task, deletion )<br/>
are reflected in the  database and thus are passed from server to current graphic representation <br/>
<hr/>
<b style="margin-left: 40%">Getting Started</b><br/>
   <a href="https://antonsyzkotaskmanagercharts.herokuapp.com/login">antonsyzkotaskmanagercharts</a><br/>
use <b>test</b> for username  and <b>test</b> for password<br/>
CREATE AND ASSIGN A NEW TASK - set priority / status / due date ( mind the date has to be  in future )/email / description (between 1 - 64 symbols)<br/>
click ADD to assign the task ( email will be sent ) or CANCEL/CLEAR<br/>
check CURRENT TASKS LIST - new task has to appear at the end of the  list ( sort if needed) <br/>
or use search window ( enter any of the params to search for the  particular  task or the group of tasks ( by email or date ) )<br/>
check graphs and charts (click on charts and graphs to check detailed info )<br/>

<hr/>

<b style="margin-left: 40%">DEMO</b><br/>
<h><a href="https://antonsyzkotaskmanagercharts.herokuapp.com/login">antonsyzkotaskmanagercharts</a></h><br/>


<hr/>

<b style="margin-left: 40%">Technologies used </b><br/>
<span>JAVA EE</span><br/>
<span>Spring Boot</span><br/>
<span>Thymeleaf</span><br/>
<span>Spring Data</span><br/>
<span>Spring Security</span><br/>
<span>Spring mail</span><br/>
<span>Postgre SQL</span><br/>
<span>Charts JS / Canvas JS</span><br/>
<span>Bootstrap / JQuery / JQuery-JTables</span><br/>


<hr/>

<b style="margin-left: 40%">Contact</b><br/>
@Anton Szyko<br/>
email:<email>antonsyzko@gmail.com</email><br/>
linkedIn : <a href="https://www.linkedin.com/in/anton-syzko-b709ab123">Anton Syzko</a><br/>
<b style="margin-left: 50%">License</b><br/>
<span>MIT</span><br/>
</body>
</html>
