/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var old_shopid = document.getElementById("shop_id").value;

    // Get-Data Click function
    //
    // Calls the GET service to lookup a currently entered shopid
    $('#get-data').click(function ()
    {
        var shopid = document.getElementById("shop_id");
        var url = 'tcss360/coffeeShop/' + shopid.value;

        $.ajax({
            type: 'GET',
            url: url,
            datatype: 'json',
            success: render_form
        });
    });

    $('#shop_id').focusin(function () {
        old_shopid = document.getElementById("shop_id").value;
    });

    // If the shop exits the shopid textbox and places focus on another
    // GUI control on the screen, and if the shop id had changed since
    // the shop entered the box, then reload the shop form
    $('#shop_id').focusout(function () {
        var curr_shopid = document.getElementById("shop_id").value;
        //alert(curr_shopid + '---' + old_shopid)
        if (curr_shopid != old_shopid)
            getshop();
    });

    // Delete-User Click function
    //
    // Calls the DELETE service to delete the selected shop
    $('#delete-shop').click(function ()
    {
        var shopid = document.getElementById("shop_id").value;
        var shopname = document.getElementById("name");
        var shop = {'shop_id': shopid};
        var url = 'tcss360/coffeeShop/' + shopid;
        if (confirm('Are you sure - DELETE ' + shopname.value + '-(' + shopid + ') ?')) {
            $.ajax({
                type: 'DELETE',
                url: url,
                datatype: 'json',
                data: JSON.stringify(shop),
                contentType: 'application/json',
                success: render_delete
            });
        }
    });

    // New-User Click function
    //
    // Clears the form to allow entry of a new shop record
    $('#new-shop').click(function ()
    {
        document.getElementById("shop_id").disabled = true;
        document.getElementById("name").value = "";
        document.getElementById("address").value = "";
        document.getElementById("city").value = "";
        document.getElementById("state").value = "";
        document.getElementById("zip").value = "";
        document.getElementById("phone").value = "";
        document.getElementById("description").value = "";
        document.getElementById("opentime").value = "";
        document.getElementById("closetime").value = "";
    });

    // Save-User Click function
    //
    // If a new shop, calls the POST to create a new shop
    // If an existing shop, call the PUT to update the shop
    $('#save-shop').click(function ()
    {
        if (document.getElementById("shop_id").disabled)
        {
            // Create a new record
            var shopname = document.getElementById("name");
            var address = document.getElementById("address");
            var city = document.getElementById("city");
            var state = document.getElementById("state");
            var zip = document.getElementById("zip");
            var phone = document.getElementById("phone");
            var description = document.getElementById("description");
            var openhours = document.getElementById("opentime");
            var closehours = document.getElementById("closetime");

            var shop = {'name': shopname.value,
                'address': address.value,
                'city': city.value,
                'state': state.value,
                'zip': zip.value,
                'phone': phone.value,
                'description': description.value,
                'opentime': openhours.value,
                'closetime': closehours.value
            };
            var url = 'tcss360/coffeeShop/';
            if (confirm('Are you sure - CREATE ' + shopname.value + ' ?')) {
                $.ajax({
                    type: 'POST',
                    url: url,
                    datatype: 'json',
                    data: JSON.stringify(shop),
                    contentType: 'application/json',
                    success: render_newshop
                });
            }
        } else
        {
            // Update an existing record
            var shopid = document.getElementById("shop_id");
            var name = document.getElementById("name");
            var address = document.getElementById("address");
            var city = document.getElementById("city");
            var state = document.getElementById("state");
            var zip = document.getElementById("zip");
            var phone = document.getElementById("phone");
            var description = document.getElementById("description");
            var opentime = document.getElementById("opentime");
            var closetime = document.getElementById("closetime");

            var shop = {
                'shopId': shopid.value,
                'name': name.value,
                'address': address.value,
                'city': city.value,
                'state': state.value,
                'zip': zip.value,
                'phone': phone.value,
                'description': description.value,
                'opentime': opentime.value,
                'closetime': closetime.value
            };
            var url = 'tcss360/coffeeShop';
            if (confirm('Are you sure - UPDATE ' + name.value + ' ?')) {
                $.ajax({
                    type: 'PUT',
                    url: url,
                    datatype: 'json',
                    data: JSON.stringify(shop),
                    contentType: 'application/json',
                    success: render_clear
                });
            }
        }
    });

    // Dynamic table event handler
    // 
    // Gets the selected shop row from the database and puts it
    // display the shop in the form.  Note, technically we could just
    // read the static table content but this approach would allow
    // you to read the entire shop record including fields which may
    // not be displayed in the HTML table.
    // 
    // Use .text() as td doesn't have method .val()
    // Empty first time as the td has no text until clicked.
    $(".table-body").on('click', 'tr', function () {
        var clicked_shopid = $(this).find('td:first').text();
        old_shopid = clicked_shopid;
        var url = 'tcss360/coffeeShop/' + clicked_shopid;
        $.ajax({
            type: 'GET',
            url: url,
            datatype: 'json',
            success: render_form
        });
    });



    // sho and display shops when the form is first loaded
    getshops();


    // Clears the form to allow entry of a new review record
    $('#clear-form').click(function ()
    {
        document.getElementById("shop_id").value = "";
        document.getElementById("name").value = "";
        document.getElementById("address").value = "";
        document.getElementById("city").value = "";
        document.getElementById("state").value = "";
        document.getElementById("zip").value = "";
        document.getElementById("phone").value = "";
        document.getElementById("description").value = "";
        document.getElementById("opentime").value = "";
        document.getElementById("closetime").value = "";
        document.getElementById("shop_id").disabled = false;
        return;
    });

});

// gets and displays shops by calling render
function getshops() {
    // Form load
    var url = 'tcss360/coffeeShop/api/shops';

    $.ajax({
        type: 'GET',
        url: url,
        datatype: 'json',
        success: render
    });
}

// gets and displays shops by calling render
function getshop() {
    // Form load
    var shopid = document.getElementById("shop_id");
    var url = 'tcss360/coffeeShop/' + shopid.value;

    $.ajax({
        type: 'GET',
        url: url,
        datatype: 'json',
        success: render_form
    });
}

// renders the shop table
function render(data) {
    $.each(data, function (index, shop) {
        var newrow = "<tr><td id=\"shop_id\"" + index + "\">" + shop.shopId + "</td>";
        newrow += "<td id=\"name\">" + shop.name + "</td>";
        newrow += "<td id=\"address\">" + shop.address + "</td>";
        newrow += "<td id=\"city\">" + shop.city + "</td>";
        newrow += "<td id=\"state\">" + shop.state + "</td>";
        newrow += "<td id=\"zip\">" + shop.zip + "</td>";
        newrow += "<td id=\"phone\">" + shop.phone + "</td>";
        newrow += "<td id=\"description\">" + shop.description + "</td>";

        newrow += "<td id=\"opentime\">" + shop.opentime + "</td>";
        newrow += "<td id=\"closetime\">" + shop.closetime + "</td>";
        // newrow += "<td id=\"maps\"><button class=\"btn btn-primary\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapseExample"+ shop.myshopId + "\" aria-expanded=\"false\" aria-controls=\"collapseExample\">Directions</button>";
        newrow += "<div class=\"collapse\" id=\"collapseExample" + shop.shopId + "\">";


        $('#mybody').append(newrow);
    });
}


function render_clear(data)
{
    $('#mybody').empty();
    document.getElementById("shop_id").disabled = false;
    getshops();
}

// clears the table, reloads the shops, clears the shop id textbox
function render_delete(data)
{
    $('#mybody').empty();
    getshops();
    document.getElementById("shop_id").value = "";
}

// display the shopid of a newly created shop as supplied from the service
function render_newshop(data)
{
    $.each(data, function (index, shop) {
        document.getElementById("shop_id").value = shop;
    });
    document.getElementById("shop_id").disabled = false;
    $('#mybody').empty();
    getshops();
}

// displays a selected shop in the form
function render_form(data) {
    var list = data;
    if (list)
        $.each(list, function (index, shop) {
            $('#shop_id').val(shop.shopId);
            $('#name').val(shop.name);
            $('#address').val(shop.address);
            $('#city').val(shop.city);
            $('#state').val(shop.state);
            $('#zip').val(shop.zip);
            $('#phone').val(shop.phone);
            $('#description').val(shop.description);

            $('#opentime').val(shop.opentime);
            $('#closetime').val(shop.closetime);
        });
    else
    {
        document.getElementById("name").value = "";
        document.getElementById("address").value = "";
        document.getElementById("city").value = "";
        document.getElementById("state").value = "";
        document.getElementById("zip").value = "";
        document.getElementById("phone").value = "";
        document.getElementById("description").value = "";
        document.getElementById("opentime").value = "";
        document.getElementById("closetime").value = "";
    }
}

 