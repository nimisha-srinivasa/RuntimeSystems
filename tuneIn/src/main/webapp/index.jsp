
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>TuneIn</title>
        <!-- Bootstrap Core CSS -->
        <link href="stylesheets/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="stylesheets/agency.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body id="page-top" class="index">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand page-scroll" href="#page-top">Tune-In</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="hidden">
                            <a href="#page-top"></a>
                        </li>
                        <%
                            UserService userService = UserServiceFactory.getUserService();
                            User user = userService.getCurrentUser();
                            if (user != null) {
                                pageContext.setAttribute("user", user);
                        %>
                        <li>
                            <a class="page-scroll" href="#services">Create</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#portfolio">Explore</a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#about"><%= user.getNickname() %></a>
                        </li>
                        <li>
                            <a class="page-scroll" href="<%= userService.createLogoutURL(request.getRequestURI()) %>">LogOut</a>
                        </li>
                        <%
                            }
                        %>
                        <li>
                            
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>
        <!-- Header -->
        <header>
            <div class="container">
                <div class="intro-text">
                    <div class="intro-lead-in">Create & Share music for free</div>
                    <div class="intro-heading">Your own Audio Studio!</div>
                    <%
                        if(user ==null){
                        
                    %>
                        <a href="<%= userService.createLoginURL(request.getRequestURI()) %>" class="btn btn-xl">Login</a>
                    <%
                        }
                    %>
                </div>
            </div>
        </header>

        <!-- Login Modal -->
        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header login_modal_header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title" id="myModalLabel">Login to Your Account</h2>
                    </div>
                    <div class="modal-body login-modal">
                        <br/>
                        <div class="clearfix"></div>
                        <div id='social-icons-conatainer'>
                            <div class='modal-body-left'>
                                <div class="form-group">
                                    <input type="text" id="username" placeholder="Enter your name" value="" class="form-control login-field">
                                    <i class="fa fa-user login-field-icon"></i>
                                </div>
                                <div class="form-group">
                                    <input type="password" id="login-pass" placeholder="Password" value="" class="form-control login-field">
                                    <i class="fa fa-lock login-field-icon"></i>
                                </div>
                                <a href="#" class="btn modal-login-btn">Login</a>
                                <a href="#" class="login-link text-center">Lost your password?</a>
                            </div>
                            <div class='modal-body-right'>
                                <div class="modal-social-icons">
                                    <a href='#' class="btn btn-default facebook"> <i class="fa fa-facebook modal-icons"></i> Sign In with Facebook </a>
                                    <a href='#' class="btn btn-default google"> <i class="fa fa-google-plus modal-icons"></i> Sign In with Google </a>
                                </div>
                            </div>
                            <div id='center-line'> OR </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="form-group modal-register-btn">
                            <button class="btn btn-default"> New User Please Register</button>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="modal-footer login_modal_footer">
                    </div>
                </div>
            </div>
        </div>
        <!-- Login Modal -->
        <%
            if(user!=null){
        %>
        <!-- Create Section -->
        <section id="services">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Create Music</h2>
                        <h3 class="section-subheading text-muted">Record an audio. Flaunt your voice.</h3>
                    </div>
                </div>
                <div class="row text-center">
                    <div class="col-md-6">
                        <span class="fa-stack fa-4x">
                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                        <a href="#about"><i class="fa fa-music fa-stack-1x fa-inverse"></i></a>
                        </span>
                        <h4 class="service-heading">Your previous creations</h4>
                        <p class="text-muted">Check out the awesome work you did before!</p>
                    </div>
                    <div class="col-md-6">
                        <a href="#audioRecordModal" data-toggle="modal">
                        <span class="fa-stack fa-4x">
                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                        <i class="fa fa-microphone fa-stack-1x fa-inverse"></i></button>
                        </span>
                        </a>
                        <h4 class="service-heading">Record a new song</h4>
                        <p class="text-muted">Sing-a-song!</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- Create Section -->
        <!-- Explore Section -->
        <section id="portfolio" class="bg-light-gray">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Explore</h2>
                        <h3 class="section-subheading text-muted">Checkout others' work. Be inspired!</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 col-sm-6 portfolio-item">
                        <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content">
                                    <i class="fa fa-youtube-play fa-3x"></i>
                                </div>
                            </div>
                            <img src="img/audio-default.jpg" class="explore img-responsive" alt="">
                        </a>
                        <div class="portfolio-caption">
                            <h4>Song1</h4>
                            <p class="text-muted">User1</p>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6 portfolio-item">
                        <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content">
                                    <i class="fa fa-youtube-play fa-3x"></i>
                                </div>
                            </div>
                            <img src="img/audio-default.jpg" class="explore img-responsive" alt="">
                        </a>
                        <div class="portfolio-caption">
                            <h4>Song2</h4>
                            <p class="text-muted">User2</p>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6 portfolio-item">
                        <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content">
                                    <i class="fa fa-youtube-play fa-3x"></i>
                                </div>
                            </div>
                            <img src="img/audio-default.jpg" class="explore img-responsive" alt="">
                        </a>
                        <div class="portfolio-caption">
                            <h4>Song3</h4>
                            <p class="text-muted">User3</p>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6 portfolio-item">
                        <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content">
                                    <i class="fa fa-youtube-play fa-3x"></i>
                                </div>
                            </div>
                            <img src="img/audio-default.jpg" class="explore img-responsive" alt="">
                        </a>
                        <div class="portfolio-caption">
                            <h4>Song4</h4>
                            <p class="text-muted">User4</p>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6 portfolio-item">
                        <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content">
                                    <i class="fa fa-youtube-play fa-3x"></i>
                                </div>
                            </div>
                            <img src="img/audio-default.jpg" class="explore img-responsive" alt="">
                        </a>
                        <div class="portfolio-caption">
                            <h4>Song5</h4>
                            <p class="text-muted">User5</p>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6 portfolio-item">
                        <a href="#portfolioModal1" class="portfolio-link" data-toggle="modal">
                            <div class="portfolio-hover">
                                <div class="portfolio-hover-content">
                                    <i class="fa fa-youtube-play fa-3x"></i>
                                </div>
                            </div>
                            <img src="img/audio-default.jpg" class="explore img-responsive" alt="">
                        </a>
                        <div class="portfolio-caption">
                            <h4>Song6</h4>
                            <p class="text-muted">User6</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- About Section -->
        <section id="about">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">About</h2>
                        <h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <ul class="timeline">
                            <li>
                                <div class="timeline-image">
                                    <img class="img-circle img-responsive" src="img/about/1.jpg" alt="">
                                </div>
                                <div class="timeline-panel">
                                    <div class="timeline-heading">
                                        <h4>2009-2011</h4>
                                        <h4 class="subheading">Our Humble Beginnings</h4>
                                    </div>
                                    <div class="timeline-body">
                                        <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                                    </div>
                                </div>
                            </li>
                            <li class="timeline-inverted">
                                <div class="timeline-image">
                                    <img class="img-circle img-responsive" src="img/about/2.jpg" alt="">
                                </div>
                                <div class="timeline-panel">
                                    <div class="timeline-heading">
                                        <h4>March 2011</h4>
                                        <h4 class="subheading">An Agency is Born</h4>
                                    </div>
                                    <div class="timeline-body">
                                        <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="timeline-image">
                                    <img class="img-circle img-responsive" src="img/about/3.jpg" alt="">
                                </div>
                                <div class="timeline-panel">
                                    <div class="timeline-heading">
                                        <h4>December 2012</h4>
                                        <h4 class="subheading">Transition to Full Service</h4>
                                    </div>
                                    <div class="timeline-body">
                                        <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                                    </div>
                                </div>
                            </li>
                            <li class="timeline-inverted">
                                <div class="timeline-image">
                                    <img class="img-circle img-responsive" src="img/about/4.jpg" alt="">
                                </div>
                                <div class="timeline-panel">
                                    <div class="timeline-heading">
                                        <h4>July 2014</h4>
                                        <h4 class="subheading">Phase Two Expansion</h4>
                                    </div>
                                    <div class="timeline-body">
                                        <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                                    </div>
                                </div>
                            </li>
                            <li class="timeline-inverted">
                                <div class="timeline-image">
                                    <h4>Be Part
                                        <br>Of Our
                                        <br>Story!
                                    </h4>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
        <!-- Team Section -->
        <section id="team" class="bg-light-gray">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Your Friends</h2>
                        <h3 class="section-subheading text-muted">Connect instantly</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <div class="team-member">
                            <img src="img/team/1.jpg" class="img-responsive img-circle" alt="">
                            <h4>Kay Garland</h4>
                            <p class="text-muted">Lead Designer</p>
                            <ul class="list-inline social-buttons">
                                <li><a href="#"><i class="fa fa-twitter"></i></a>
                                </li>
                                <li><a href="#"><i class="fa fa-facebook"></i></a>
                                </li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="team-member">
                            <img src="img/team/2.jpg" class="img-responsive img-circle" alt="">
                            <h4>Larry Parker</h4>
                            <p class="text-muted">Lead Marketer</p>
                            <ul class="list-inline social-buttons">
                                <li><a href="#"><i class="fa fa-twitter"></i></a>
                                </li>
                                <li><a href="#"><i class="fa fa-facebook"></i></a>
                                </li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="team-member">
                            <img src="img/team/3.jpg" class="img-responsive img-circle" alt="">
                            <h4>Diana Pertersen</h4>
                            <p class="text-muted">Lead Developer</p>
                            <ul class="list-inline social-buttons">
                                <li><a href="#"><i class="fa fa-twitter"></i></a>
                                </li>
                                <li><a href="#"><i class="fa fa-facebook"></i></a>
                                </li>
                                <li><a href="#"><i class="fa fa-linkedin"></i></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 text-center">
                        <p class="large text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut eaque, laboriosam veritatis, quos non quis ad perspiciatis, totam corporis ea, alias ut unde.</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- Contact Section -->
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <span class="copyright">Copyright &copy; TuneIn  for CS263 2016</span>
                    </div>
                    <div class="col-md-4">
                        <ul class="list-inline social-buttons">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-4">
                        <ul class="list-inline quicklinks">
                            <li><a href="#">Privacy Policy</a>
                            </li>
                            <li><a href="#">Terms of Use</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>

        <!-- Modals begin here -->
        <!-- Audio Record Modal -->
        <div class="audio-modal modal fade" id="audioRecordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <a class="audio-modal close-modal" data-dismiss="modal"><i class="fa fa-3x fa-times"></i></a>
                        <h2>Record a Song now!</h2>
                        <%
                            if(user!=null){
                        %>
                        <p class="item-intro text-muted">By <%= user.getNickname()%></p>
                        <%
                            }
                            else{
                        %>
                        <p class="item-intro text-muted">By Guest</p>
                        <%
                            }
                        %>
                        <div>
                        <span class="fa-stack fa-4x">
                            <i class="fa fa-circle fa-stack-2x text-primary" style="color:#E82D2D"></i>
                            <i class="fa fa-microphone fa-stack-1x fa-inverse"></i>
                        </span>
                        <br/>
                        <br/>
                        </div>
                        <div>
                        <button type="button" class="btn btn-primary"><i class="fa fa-save"></i> Save Audio</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button>
                        </div>
                        <br/>
                        <br/>
                    </div>
                    <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Audio Record Modal -->

        <!-- Portfolio Modals -->
        <!-- Use the modals below to showcase details about your portfolio projects! -->
        <!-- Portfolio Modal 1 -->
        <div class="audio-modal modal fade" id="portfolioModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <a class="audio-modal close-modal" data-dismiss="modal"><i class="fa fa-3x fa-times"></i></a>
                        <h2>Song 1</h2>
                        <p class="item-intro text-muted">By User1</p>
                        <img class="portfolio-img img-responsive img-centered" src="img/audio-default.jpg" alt="">
                        <br/>
                        <audio controls>
                            <source src="http://www.w3schools.com/html/horse.ogg" type="audio/ogg" />
                            <source src="http://www.w3schools.com/html/horse.mp3" type="audio/mpeg" />
                            <a href="http://www.w3schools.com/html/horse.mp3">horse</a>
                        </audio>
                        <br/>
                        <br/>
                        <ul class="list-inline">
                            <li>Date: July 2014</li>
                            <li>Client: Round Icons</li>
                            <li>Category: Graphic Design</li>
                        </ul>
                        <br/>
                    </div>
                    <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Plugin JavaScript -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
        <script src="js/classie.js"></script>
        <script src="js/cbpAnimatedHeader.js"></script>
        <!-- Contact Form JavaScript -->
        <script src="js/jqBootstrapValidation.js"></script>
        <script src="js/contact_me.js"></script>
        <!-- Custom Theme JavaScript -->
        <script src="js/agency.js"></script>
    </body>
</html>