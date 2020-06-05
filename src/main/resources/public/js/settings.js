(function($) {
  'use strict';
  $(function() {
    $(".nav-settings").click(function(){
      $("#right-sidebar").toggleClass("open");
    });
    $(".settings-close").click(function(){
      $("#right-sidebar,#theme-settings").removeClass("open");
    });

    $("#settings-trigger").on("click" , function(){
      $("#theme-settings").toggleClass("open");
    });


    //background constants
    var navbar_classes = "navbar-danger navbar-success navbar-warning navbar-dark navbar-light navbar-primary navbar-info navbar-pink";
    var sidebar_classes = "sidebar-light sidebar-dark";
    var $body = $("body");    
  });
})(jQuery);
