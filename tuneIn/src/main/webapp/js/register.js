hello.init({
    google: '36542558745-tcmhfqeondfsvoo6ppq143tkfs51d6um.apps.googleusercontent.com'
}, 
{
    redirect_uri: 'index2.html',
    scope: 'email'
},
{
	oauth_proxy: 'https://auth-server.herokuapp.com/proxy'
});

hello.on('auth.login', function(auth) {

    // Call user information, for the given network
    hello(auth.network).api('/me').then(function(r) {
        //do something
        user=r;
        window.location = "index2.html";
    });
});

function login(){
	hello('google').login();    
}