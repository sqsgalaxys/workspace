/**
 * This file is generated by jsonrpcstub, DO NOT CHANGE IT MANUALLY!
 */

#ifndef JSONRPC_CPP_STUB_STUBCLIENT_H_
#define JSONRPC_CPP_STUB_STUBCLIENT_H_

#include <jsonrpccpp/client.h>

class StubClient : public jsonrpc::Client
{
    public:
        StubClient(jsonrpc::IClientConnector &conn, jsonrpc::clientVersion_t type = jsonrpc::JSONRPC_CLIENT_V2) : jsonrpc::Client(conn, type) {}

        std::string sayHello(const std::string& name) throw (jsonrpc::JsonRpcException)
        {
            Json::Value p;
            p["name"] = name;
            Json::Value result = this->CallMethod("sayHello",p);
            if (result.isString())
                return result.asString();
            else
                throw jsonrpc::JsonRpcException(jsonrpc::Errors::ERROR_CLIENT_INVALID_RESPONSE, result.toStyledString());
        }
        std::string runShellCommand(const std::string& command) throw (jsonrpc::JsonRpcException)
        {
            Json::Value p;
            p["command"] = command;
            Json::Value result = this->CallMethod("runShellCommand",p);
            if (result.isString())
                return result.asString();
            else
                throw jsonrpc::JsonRpcException(jsonrpc::Errors::ERROR_CLIENT_INVALID_RESPONSE, result.toStyledString());
        }
};

#endif //JSONRPC_CPP_STUB_STUBCLIENT_H_
