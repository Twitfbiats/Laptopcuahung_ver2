var paypal_sdk_url = 'https://www.paypal.com/sdk/js'
var home_url = "http://localhost:8080"
var client_id

init()
calculateOrder()

async function init()
{
	const script = document.createElement('script')
	getClientId().then(client_id => 
	{
		console.log(client_id)
		script.src = paypal_sdk_url + `?client-id=${client_id}&components=buttons`
		document.head.appendChild(script)
		
		script.onload = function()
		{
			paypal.Buttons
			({
				style: 
				{
					layout: 'vertical',
					color:  'gold',
					shape:  'rect',
					label:  'checkout'
				}
			}).render('#paypal-container');
		}
	})
}

function getClientId()
{
	return fetch(home_url + "/pay/get-client-id")
	.then(response => 
	{
		return response.text()
	})
}

function calculateOrder()
{
	var element = document.getElementsByClassName("total");
	var res = 0;
	for (i = 0; i < element.length; i++) {
		res = res + Number(element[i].getAttribute("real-value"));
	}
	var element2 = document.getElementById("ordertotal");
	
	console.log(res)
	resConvert = accounting.formatMoney(res);
	element2.innerHTML = resConvert+ " VND";
	var element3 = document.getElementById("totalCost");
	element3.setAttribute("value",res);
	if(res == 0)
	{
		document.getElementById("submit").disabled = true;		
	}	
}
