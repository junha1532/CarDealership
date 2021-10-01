$(document).ready(function () {
  setTimeout(function () {
    $('#reportrange').click();
    if (getUrlParameter('user') === false) {
      $('#report-form button').click();
    }
  }, 300);
});

$(function () {
  var start = moment().subtract(29, 'days');
  var end = moment();

  function cb(start, end) {
    $('#reportrange span').html(
      start.format('YYYY-MM-DD') + '-' + end.format('YYYY-MM-DD')
    );
  }

  $('#reportrange').daterangepicker(
    {
      startDate: start,
      endDate: end,
      ranges: {
        Today: [moment(), moment()],
        Yesterday: [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        'Last 7 Days': [moment().subtract(6, 'days'), moment()],
        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
        'This Month': [moment().startOf('month'), moment().endOf('month')],
        'Last Month': [
          moment().subtract(1, 'month').startOf('month'),
          moment().subtract(1, 'month').endOf('month'),
        ],
        'Last Quarter': [
          moment().subtract(3, 'month').startOf('month'),
          moment().subtract(1, 'month').endOf('month'),
        ],
        'Last Year': [
          moment().subtract(1, 'year').startOf('year'),
          moment().subtract(1, 'year').endOf('year'),
        ],
      },
    },
    cb
  );

  cb(start, end);
});

$('#reportrange').on('show.daterangepicker', function (ev, picker) {
  $('input#dateFrom').val(picker.startDate.format('YYYY-MM-DD'));
  $('input#dateUntil').val(picker.endDate.format('YYYY-MM-DD'));
});

$('#reportrange').on('hide.daterangepicker', function (ev, picker) {
  $('input#dateFrom').val(picker.startDate.format('YYYY-MM-DD'));
  $('input#dateUntil').val(picker.endDate.format('YYYY-MM-DD'));
});

function getUrlParameter(sParam) {
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
}

// "YYYY-MM-DD" format -> "MM/DD/YYYY"
function formatDateString(dateString) {
  return (
    dateString.split('-')[1] +
    '/' +
    dateString.split('-')[2] +
    '/' +
    dateString.split('-')[0]
  );
}
