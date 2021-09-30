$(document).ready(function () {
  setTimeout(function () {
    if (!getUrlParameter('makeName')) {
      $.ajax({
        url: 'editVehicle',
        data: {
          id: $('input#vehicleId').val(),
          makeName: $('select#make').val(),
        },
        type: 'GET',
        success: function (response) {
          window.location.href =
            '/admin/editVehicle?id=' +
            $('input#vehicleId').val() +
            '&makeName=' +
            $('select#make').val();
        },
        error: function (xhr) {
          console.log('fail');
        },
      });
    }
  }, 500);

  $('select#make').on('change', function () {
    $.ajax({
      url: 'editVehicle',
      data: {
        id: $('input#vehicleId').val(),
        makeName: $('select#make').val(),
      },
      type: 'GET',
      success: function (response) {
        window.location.href =
          '/admin/editVehicle?id=' +
          $('input#vehicleId').val() +
          '&makeName=' +
          $('select#make').val();
      },
      error: function (xhr) {
        console.log('fail');
      },
    });
  });
});

var getUrlParameter = function getUrlParameter(sParam) {
  var sPageURL = window.location.search.substring(1),
    sURLVariables = sPageURL.split('&'),
    sParameterName,
    i;

  for (i = 0; i < sURLVariables.length; i++) {
    sParameterName = sURLVariables[i].split('=');

    if (sParameterName[0] === sParam) {
      return typeof sParameterName[1] === undefined
        ? true
        : decodeURIComponent(sParameterName[1]);
    }
  }
  return false;
};
