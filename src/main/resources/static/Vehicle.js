$(document).ready(function () {
  var minPrice = 20000;
  var maxPrice = 50000;
  var minYear = 2018;
  var maxYear = 2021;
  $('.js-price-slider').ionRangeSlider({
    type: 'double',
    grid: true,
    min: 0,
    max: 100000,
    from: minPrice,
    to: maxPrice,
    prefix: '$',
  });
  $('.js-year-slider').ionRangeSlider({
    type: 'double',
    prettify_enabled: false,
    grid: true,
    min: 2000,
    max: 2021,
    from: minYear,
    to: maxYear,
    prefix: 'Year ',
  });
  $('input#minPrice').val(minPrice);
  $('input#maxPrice').val(maxPrice);
  $('input#minYear').val(minYear);
  $('input#maxYear').val(maxYear);
});

$('.js-price-slider').on('change', function () {
  var $inp = $(this);
  var value = $inp.prop('value').split(';');

  $('input#minPrice').val(value[0]);
  $('input#maxPrice').val(value[1]);
});

$('.js-year-slider').on('change', function () {
  var $inp = $(this);
  var value = $inp.prop('value').split(';');

  $('input#minYear').val(value[0]);
  $('input#maxYear').val(value[1]);
});
