hello.init({
    google: '36542558745-placeneqlea01hiat8rednod6cqkiq36.apps.googleusercontent.com'
}, 
{
    redirect_uri: 'login.html',
    scope: 'email'
});
//hello.logout() doesnt work for google plus!!
function logout(){
	alert("logging out");
	hello.logout();
	window.location = "login.html";
}
