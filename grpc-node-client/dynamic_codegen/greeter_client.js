var PROTO_PATH = __dirname + '/../proto/pingpong.proto';

var parseArgs = require('minimist');
var grpc = require('@grpc/grpc-js');
var protoLoader = require('@grpc/proto-loader');
var packageDefinition = protoLoader.loadSync(
    PROTO_PATH,
    {keepCase: true,
     longs: String,
     enums: String,
     defaults: true,
     oneofs: true
    });
var ping_proto = grpc.loadPackageDefinition(packageDefinition).stubs;

function main() {
  var target = 'localhost:8080';
  var client = new ping_proto.PingPongService(target,
                                       grpc.credentials.createInsecure());
  var message = "Ping";
  client.pingPong({message: message, timestamp: Date.now()}, function(err, response) {
   if (err) {
  console.log('error:', err);
    } else {
    console.log(`Response: ${response.message} at ${response.timestamp}`);
    }
  });
}

main();
