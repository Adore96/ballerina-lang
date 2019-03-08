import ballerina/io;

public function serialize(BType bType) returns string {
    if (bType == "()"){
        return "()";
    } else if (bType == "int"){
        return "int";
    } else if (bType == "byte"){
        return "byte";
    } else if (bType == "boolean"){
        return "boolean";
    } else if (bType == "float"){
        return "float";
    } else if (bType == "string"){
        return "string";
    } else if (bType is BUnionType) {
        return serializeTypes(bType.members, "|");
    } else if (bType is BInvokableType) {
        return "function (" + serializeTypes(bType.paramTypes, ", ") + ") returns " + serialize(bType.retType);
    } else if (bType is BArrayType) {
        return serialize(bType.eType) + "[]";
    } else if (bType is BObjectType) {
        return "object {" + serializeFields(bType.fields) + "}";
    }

    error err = error("Unsupported type serializtion ");
    panic err;
}

function serializeTypes(BType[] bTypes, string delimiter) returns string {
    var result = "";
    boolean first = true;
    foreach var bType in bTypes {
        if (!first){
            result = result + delimiter;
        }
        result = result + serialize(bType);
        first = false;
    }
    return result;
}

function serializeFields(BObjectField[] fields) returns string {
    var result = "";
    var delimiter = ";";
    foreach var field in fields {
        result = result + serialize(field.typeValue) + " " + field.name.value + delimiter;
    }
    return result;
}
