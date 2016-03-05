
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
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
        <link href="stylesheets/my_stylesheet.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script type="text/javascript" src="js/hello.min.js"></script>
        <script type="text/javascript" src="js/index.js"></script>
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
                <div id="navigationBar">
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="hidden">
                                <a href="#page-top"></a>
                            </li>
                            <li>
                                <a class="page-scroll" href="#services">Create</a>
                            </li>
                            <li>
                                <a class="page-scroll" href="#portfolio">Explore</a>
                            </li>
                            
                            <li>
                                <a class="page-scroll" id="userName" href="#about"></a>
                            </li>
                            <li>
                                <a class="page-scroll" href="FIGURE_IT_OUT">LogOut</a>
                            </li>
                            <li>
                                <button class="btn btn-xl" onclick="logout()">LogOut</a>
                            </li>
                            <li>
                                
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- navigationBar -->
            </div>
            <!-- /.container-fluid -->
        </nav>
        <!-- Header -->
        <header>
            <div class="container">
                <div class="intro-text">
                    <div class="intro-lead-in">Create & Share music for free</div>
                    <div class="intro-heading">Your own Audio Studio!</div>
                </div>
            </div>
        </header>
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
            <div class="container" id="usersPrevCreations">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Your Previous Creations</h2>
                        <h3 class="section-subheading text-muted">This is the awesome work you did before!</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12" >
                        <ul class="timeline" id="myPrevWork">
                        </ul>
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
                        <div>
                            <span class="fa-stack fa-4x">
                                <i class="fa fa-circle fa-stack-2x text-primary" style="color:#E82D2D"></i>
                                <i class="fa fa-microphone fa-stack-1x fa-inverse"></i>
                            </span>
                            <br/>
                            <br/>
                        </div>
                        <form action="<%= blobstoreService.createUploadUrl("/rest/audioClip") %>"  method="POST" enctype="multipart/form-data">
                            <input type="hidden" id="audioSubmit_userId" name="userId"/>
                            <div>
                                <label> title:</label><input type="text" name="title">
                            </div>
                            <div>
                                <label> Image:</label><input type="file" name="myImage">
                            </div>
                            <div>
                                <label> Audio:</label><input type="file" name="myAudio">
                            </div>
                            <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> Save Audio</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button>
                        </form>
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