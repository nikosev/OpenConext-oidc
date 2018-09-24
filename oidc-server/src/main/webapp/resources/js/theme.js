$(document).ready(function() {
  if (!Cookies.get('cookies_accepted')) {
    $('#cookies').show();
  };


  $('#js-accept-cookies').click(function(e){
    e.preventDefault();
    $('#cookies').hide();
    Cookies.set('cookies_accepted', true);
  })
});
