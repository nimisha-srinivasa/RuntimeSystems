hello.init({
    google: '36542558745-placeneqlea01hiat8rednod6cqkiq36.apps.googleusercontent.com'
}, 
{
    redirect_uri: 'index.html',
    scope: 'email'
},
{
	oauth_proxy: 'https://auth-server.herokuapp.com/proxy'
});

hello.on('auth.login', function(auth) {

    // Call user information, for the given network
    hello(auth.network).api('/me').then(function(r) {
        //do something
    });
});
function login(){
	alert("logging in");
	hello('google').login();
	window.location = "index.html";
}
