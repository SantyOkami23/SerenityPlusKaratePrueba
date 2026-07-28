function fn() {
  var env = karate.env; // get java system property 'karate.env'
  karate.log('karate.env system property was:', env);
  
  if (!env) {
    env = 'dev'; // a custom 'default' environment
  }
  
  var config = {
    baseUrl: 'https://automationexercise.com'
  };
  
  // configure headers genéricos
  karate.configure('headers', {
    'Accept': 'application/json, text/plain, */*',
    'Content-Type': 'application/x-www-form-urlencoded'
  });
  
  // timeouts
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  
  return config;
}
