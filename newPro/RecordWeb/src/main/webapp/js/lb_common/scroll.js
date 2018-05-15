 $.fn.spin = function(opts) {
    this.each(function() {
      var $this = $(this),
          data = $this.data();

      if (data.spinner) {
        data.spinner.stop();
        delete data.spinner;
      }
      if (opts !== false) {
        data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
      }
    });
    return this;
  };
  //$('#dot').spin();
  prettyPrint();
  function update() {
    var opts = {};
    $('#opts input[min], #opts select').each(function() {
      $('#opt-' + this.name).text(opts[this.name] = parseFloat(this.value));
	 
    });
    $('#opts input:checkbox').each(function() {
      opts[this.name] = this.checked;
      $('#opt-' + this.name).text(this.checked);
    });
    $('#preview').spin(opts);
    if ($('#share').is(':checked')) {
      window.location.replace('#?' + $('form').serialize());
    }
  }
  $(function() {
    var params = {};
    var hash = /^#\?(.*)/.exec(location.hash);
    if (hash) {
      $('#share').prop('checked', true);
      $.each(hash[1].split(/&/), function(i, pair) {
        var kv = pair.split(/=/);
        params[kv[0]] = kv[kv.length-1];
      });
    }
    $('#opts input[min], #opts select').each(function() {
      var val = params[this.name];
      if (val !== undefined) this.value = val;
      this.onchange = update;
    });
    $('#opts input:checkbox').each(function() {
      this.checked = !!params[this.name];
      this.onclick = update;
    });
    $('#share').click(function() {
      window.location.replace(this.checked ? '#?' + $('form').serialize() : '#!');
    });
    update();
  });// JavaScript Document
 