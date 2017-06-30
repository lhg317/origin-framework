/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.role.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-06-30")
public class RpcRoleObject implements org.apache.thrift.TBase<RpcRoleObject, RpcRoleObject._Fields>, java.io.Serializable, Cloneable, Comparable<RpcRoleObject> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcRoleObject");

  private static final org.apache.thrift.protocol.TField ROLE_OBJECT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("roleObjectID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ROLE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("roleID", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ROLE_OBJECT_FIELD_DESC = new org.apache.thrift.protocol.TField("roleObject", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcRoleObjectStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcRoleObjectTupleSchemeFactory();

  private java.lang.String roleObjectID; // required
  private java.lang.String roleID; // required
  private java.lang.String roleObject; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROLE_OBJECT_ID((short)1, "roleObjectID"),
    ROLE_ID((short)2, "roleID"),
    ROLE_OBJECT((short)3, "roleObject");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ROLE_OBJECT_ID
          return ROLE_OBJECT_ID;
        case 2: // ROLE_ID
          return ROLE_ID;
        case 3: // ROLE_OBJECT
          return ROLE_OBJECT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROLE_OBJECT_ID, new org.apache.thrift.meta_data.FieldMetaData("roleObjectID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROLE_ID, new org.apache.thrift.meta_data.FieldMetaData("roleID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROLE_OBJECT, new org.apache.thrift.meta_data.FieldMetaData("roleObject", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcRoleObject.class, metaDataMap);
  }

  public RpcRoleObject() {
  }

  public RpcRoleObject(
    java.lang.String roleObjectID,
    java.lang.String roleID,
    java.lang.String roleObject)
  {
    this();
    this.roleObjectID = roleObjectID;
    this.roleID = roleID;
    this.roleObject = roleObject;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcRoleObject(RpcRoleObject other) {
    if (other.isSetRoleObjectID()) {
      this.roleObjectID = other.roleObjectID;
    }
    if (other.isSetRoleID()) {
      this.roleID = other.roleID;
    }
    if (other.isSetRoleObject()) {
      this.roleObject = other.roleObject;
    }
  }

  public RpcRoleObject deepCopy() {
    return new RpcRoleObject(this);
  }

  @Override
  public void clear() {
    this.roleObjectID = null;
    this.roleID = null;
    this.roleObject = null;
  }

  public java.lang.String getRoleObjectID() {
    return this.roleObjectID;
  }

  public void setRoleObjectID(java.lang.String roleObjectID) {
    this.roleObjectID = roleObjectID;
  }

  public void unsetRoleObjectID() {
    this.roleObjectID = null;
  }

  /** Returns true if field roleObjectID is set (has been assigned a value) and false otherwise */
  public boolean isSetRoleObjectID() {
    return this.roleObjectID != null;
  }

  public void setRoleObjectIDIsSet(boolean value) {
    if (!value) {
      this.roleObjectID = null;
    }
  }

  public java.lang.String getRoleID() {
    return this.roleID;
  }

  public void setRoleID(java.lang.String roleID) {
    this.roleID = roleID;
  }

  public void unsetRoleID() {
    this.roleID = null;
  }

  /** Returns true if field roleID is set (has been assigned a value) and false otherwise */
  public boolean isSetRoleID() {
    return this.roleID != null;
  }

  public void setRoleIDIsSet(boolean value) {
    if (!value) {
      this.roleID = null;
    }
  }

  public java.lang.String getRoleObject() {
    return this.roleObject;
  }

  public void setRoleObject(java.lang.String roleObject) {
    this.roleObject = roleObject;
  }

  public void unsetRoleObject() {
    this.roleObject = null;
  }

  /** Returns true if field roleObject is set (has been assigned a value) and false otherwise */
  public boolean isSetRoleObject() {
    return this.roleObject != null;
  }

  public void setRoleObjectIsSet(boolean value) {
    if (!value) {
      this.roleObject = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ROLE_OBJECT_ID:
      if (value == null) {
        unsetRoleObjectID();
      } else {
        setRoleObjectID((java.lang.String)value);
      }
      break;

    case ROLE_ID:
      if (value == null) {
        unsetRoleID();
      } else {
        setRoleID((java.lang.String)value);
      }
      break;

    case ROLE_OBJECT:
      if (value == null) {
        unsetRoleObject();
      } else {
        setRoleObject((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ROLE_OBJECT_ID:
      return getRoleObjectID();

    case ROLE_ID:
      return getRoleID();

    case ROLE_OBJECT:
      return getRoleObject();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ROLE_OBJECT_ID:
      return isSetRoleObjectID();
    case ROLE_ID:
      return isSetRoleID();
    case ROLE_OBJECT:
      return isSetRoleObject();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcRoleObject)
      return this.equals((RpcRoleObject)that);
    return false;
  }

  public boolean equals(RpcRoleObject that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_roleObjectID = true && this.isSetRoleObjectID();
    boolean that_present_roleObjectID = true && that.isSetRoleObjectID();
    if (this_present_roleObjectID || that_present_roleObjectID) {
      if (!(this_present_roleObjectID && that_present_roleObjectID))
        return false;
      if (!this.roleObjectID.equals(that.roleObjectID))
        return false;
    }

    boolean this_present_roleID = true && this.isSetRoleID();
    boolean that_present_roleID = true && that.isSetRoleID();
    if (this_present_roleID || that_present_roleID) {
      if (!(this_present_roleID && that_present_roleID))
        return false;
      if (!this.roleID.equals(that.roleID))
        return false;
    }

    boolean this_present_roleObject = true && this.isSetRoleObject();
    boolean that_present_roleObject = true && that.isSetRoleObject();
    if (this_present_roleObject || that_present_roleObject) {
      if (!(this_present_roleObject && that_present_roleObject))
        return false;
      if (!this.roleObject.equals(that.roleObject))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRoleObjectID()) ? 131071 : 524287);
    if (isSetRoleObjectID())
      hashCode = hashCode * 8191 + roleObjectID.hashCode();

    hashCode = hashCode * 8191 + ((isSetRoleID()) ? 131071 : 524287);
    if (isSetRoleID())
      hashCode = hashCode * 8191 + roleID.hashCode();

    hashCode = hashCode * 8191 + ((isSetRoleObject()) ? 131071 : 524287);
    if (isSetRoleObject())
      hashCode = hashCode * 8191 + roleObject.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcRoleObject other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRoleObjectID()).compareTo(other.isSetRoleObjectID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoleObjectID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roleObjectID, other.roleObjectID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRoleID()).compareTo(other.isSetRoleID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoleID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roleID, other.roleID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRoleObject()).compareTo(other.isSetRoleObject());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoleObject()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roleObject, other.roleObject);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcRoleObject(");
    boolean first = true;

    sb.append("roleObjectID:");
    if (this.roleObjectID == null) {
      sb.append("null");
    } else {
      sb.append(this.roleObjectID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("roleID:");
    if (this.roleID == null) {
      sb.append("null");
    } else {
      sb.append(this.roleID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("roleObject:");
    if (this.roleObject == null) {
      sb.append("null");
    } else {
      sb.append(this.roleObject);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RpcRoleObjectStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcRoleObjectStandardScheme getScheme() {
      return new RpcRoleObjectStandardScheme();
    }
  }

  private static class RpcRoleObjectStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcRoleObject> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcRoleObject struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROLE_OBJECT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roleObjectID = iprot.readString();
              struct.setRoleObjectIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ROLE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roleID = iprot.readString();
              struct.setRoleIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ROLE_OBJECT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roleObject = iprot.readString();
              struct.setRoleObjectIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcRoleObject struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.roleObjectID != null) {
        oprot.writeFieldBegin(ROLE_OBJECT_ID_FIELD_DESC);
        oprot.writeString(struct.roleObjectID);
        oprot.writeFieldEnd();
      }
      if (struct.roleID != null) {
        oprot.writeFieldBegin(ROLE_ID_FIELD_DESC);
        oprot.writeString(struct.roleID);
        oprot.writeFieldEnd();
      }
      if (struct.roleObject != null) {
        oprot.writeFieldBegin(ROLE_OBJECT_FIELD_DESC);
        oprot.writeString(struct.roleObject);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcRoleObjectTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcRoleObjectTupleScheme getScheme() {
      return new RpcRoleObjectTupleScheme();
    }
  }

  private static class RpcRoleObjectTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcRoleObject> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcRoleObject struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRoleObjectID()) {
        optionals.set(0);
      }
      if (struct.isSetRoleID()) {
        optionals.set(1);
      }
      if (struct.isSetRoleObject()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRoleObjectID()) {
        oprot.writeString(struct.roleObjectID);
      }
      if (struct.isSetRoleID()) {
        oprot.writeString(struct.roleID);
      }
      if (struct.isSetRoleObject()) {
        oprot.writeString(struct.roleObject);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcRoleObject struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.roleObjectID = iprot.readString();
        struct.setRoleObjectIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.roleID = iprot.readString();
        struct.setRoleIDIsSet(true);
      }
      if (incoming.get(2)) {
        struct.roleObject = iprot.readString();
        struct.setRoleObjectIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

