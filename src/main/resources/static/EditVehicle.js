$('document').ready(function () {
  $('select#make').on('chage', function () {
    jQuery.get('/queryModels');
  });
});
