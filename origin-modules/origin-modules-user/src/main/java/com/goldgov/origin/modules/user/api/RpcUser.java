/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.user.api;

import com.goldgov.origin.core.web.validator.annotation.Min;
import com.goldgov.origin.core.web.validator.annotation.NotNull;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-04-18")
public class RpcUser implements org.apache.thrift.TBase<RpcUser, RpcUser._Fields>, java.io.Serializable, Cloneable, Comparable<RpcUser> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcUser");

  private static final org.apache.thrift.protocol.TField USER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("userID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField USER_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("userName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField LOGIN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("loginName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PASSWORD_FIELD_DESC = new org.apache.thrift.protocol.TField("password", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField EMAIL_FIELD_DESC = new org.apache.thrift.protocol.TField("email", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcUserStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcUserTupleSchemeFactory();

  private java.lang.String userID; // required
  private java.lang.String userName; // required
  private java.lang.String loginName; // required
  private java.lang.String password; // required
  private java.lang.String email; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    USER_ID((short)1, "userID"),
    USER_NAME((short)2, "userName"),
    LOGIN_NAME((short)3, "loginName"),
    PASSWORD((short)4, "password"),
    EMAIL((short)5, "email");

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
        case 1: // USER_ID
          return USER_ID;
        case 2: // USER_NAME
          return USER_NAME;
        case 3: // LOGIN_NAME
          return LOGIN_NAME;
        case 4: // PASSWORD
          return PASSWORD;
        case 5: // EMAIL
          return EMAIL;
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
    tmpMap.put(_Fields.USER_ID, new org.apache.thrift.meta_data.FieldMetaData("userID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.USER_NAME, new org.apache.thrift.meta_data.FieldMetaData("userName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LOGIN_NAME, new org.apache.thrift.meta_data.FieldMetaData("loginName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PASSWORD, new org.apache.thrift.meta_data.FieldMetaData("password", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EMAIL, new org.apache.thrift.meta_data.FieldMetaData("email", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcUser.class, metaDataMap);
  }

  public RpcUser() {
  }

  public RpcUser(
    java.lang.String userID,
    java.lang.String userName,
    java.lang.String loginName,
    java.lang.String password,
    java.lang.String email)
  {
    this();
    this.userID = userID;
    this.userName = userName;
    this.loginName = loginName;
    this.password = password;
    this.email = email;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcUser(RpcUser other) {
    if (other.isSetUserID()) {
      this.userID = other.userID;
    }
    if (other.isSetUserName()) {
      this.userName = other.userName;
    }
    if (other.isSetLoginName()) {
      this.loginName = other.loginName;
    }
    if (other.isSetPassword()) {
      this.password = other.password;
    }
    if (other.isSetEmail()) {
      this.email = other.email;
    }
  }

  public RpcUser deepCopy() {
    return new RpcUser(this);
  }

  @Override
  public void clear() {
    this.userID = null;
    this.userName = null;
    this.loginName = null;
    this.password = null;
    this.email = null;
  }

  public java.lang.String getUserID() {
    return this.userID;
  }

  public void setUserID(java.lang.String userID) {
    this.userID = userID;
  }

  public void unsetUserID() {
    this.userID = null;
  }

  /** Returns true if field userID is set (has been assigned a value) and false otherwise */
  public boolean isSetUserID() {
    return this.userID != null;
  }

  public void setUserIDIsSet(boolean value) {
    if (!value) {
      this.userID = null;
    }
  }

  public java.lang.String getUserName() {
    return this.userName;
  }

  public void setUserName(java.lang.String userName) {
    this.userName = userName;
  }

  public void unsetUserName() {
    this.userName = null;
  }

  /** Returns true if field userName is set (has been assigned a value) and false otherwise */
  public boolean isSetUserName() {
    return this.userName != null;
  }

  public void setUserNameIsSet(boolean value) {
    if (!value) {
      this.userName = null;
    }
  }

  public java.lang.String getLoginName() {
    return this.loginName;
  }

  public void setLoginName(java.lang.String loginName) {
    this.loginName = loginName;
  }

  public void unsetLoginName() {
    this.loginName = null;
  }

  /** Returns true if field loginName is set (has been assigned a value) and false otherwise */
  public boolean isSetLoginName() {
    return this.loginName != null;
  }

  public void setLoginNameIsSet(boolean value) {
    if (!value) {
      this.loginName = null;
    }
  }

  public java.lang.String getPassword() {
    return this.password;
  }

  public void setPassword(java.lang.String password) {
    this.password = password;
  }

  public void unsetPassword() {
    this.password = null;
  }

  /** Returns true if field password is set (has been assigned a value) and false otherwise */
  public boolean isSetPassword() {
    return this.password != null;
  }

  public void setPasswordIsSet(boolean value) {
    if (!value) {
      this.password = null;
    }
  }

  public java.lang.String getEmail() {
    return this.email;
  }

  public void setEmail(java.lang.String email) {
    this.email = email;
  }

  public void unsetEmail() {
    this.email = null;
  }

  /** Returns true if field email is set (has been assigned a value) and false otherwise */
  public boolean isSetEmail() {
    return this.email != null;
  }

  public void setEmailIsSet(boolean value) {
    if (!value) {
      this.email = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case USER_ID:
      if (value == null) {
        unsetUserID();
      } else {
        setUserID((java.lang.String)value);
      }
      break;

    case USER_NAME:
      if (value == null) {
        unsetUserName();
      } else {
        setUserName((java.lang.String)value);
      }
      break;

    case LOGIN_NAME:
      if (value == null) {
        unsetLoginName();
      } else {
        setLoginName((java.lang.String)value);
      }
      break;

    case PASSWORD:
      if (value == null) {
        unsetPassword();
      } else {
        setPassword((java.lang.String)value);
      }
      break;

    case EMAIL:
      if (value == null) {
        unsetEmail();
      } else {
        setEmail((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case USER_ID:
      return getUserID();

    case USER_NAME:
      return getUserName();

    case LOGIN_NAME:
      return getLoginName();

    case PASSWORD:
      return getPassword();

    case EMAIL:
      return getEmail();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case USER_ID:
      return isSetUserID();
    case USER_NAME:
      return isSetUserName();
    case LOGIN_NAME:
      return isSetLoginName();
    case PASSWORD:
      return isSetPassword();
    case EMAIL:
      return isSetEmail();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcUser)
      return this.equals((RpcUser)that);
    return false;
  }

  public boolean equals(RpcUser that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_userID = true && this.isSetUserID();
    boolean that_present_userID = true && that.isSetUserID();
    if (this_present_userID || that_present_userID) {
      if (!(this_present_userID && that_present_userID))
        return false;
      if (!this.userID.equals(that.userID))
        return false;
    }

    boolean this_present_userName = true && this.isSetUserName();
    boolean that_present_userName = true && that.isSetUserName();
    if (this_present_userName || that_present_userName) {
      if (!(this_present_userName && that_present_userName))
        return false;
      if (!this.userName.equals(that.userName))
        return false;
    }

    boolean this_present_loginName = true && this.isSetLoginName();
    boolean that_present_loginName = true && that.isSetLoginName();
    if (this_present_loginName || that_present_loginName) {
      if (!(this_present_loginName && that_present_loginName))
        return false;
      if (!this.loginName.equals(that.loginName))
        return false;
    }

    boolean this_present_password = true && this.isSetPassword();
    boolean that_present_password = true && that.isSetPassword();
    if (this_present_password || that_present_password) {
      if (!(this_present_password && that_present_password))
        return false;
      if (!this.password.equals(that.password))
        return false;
    }

    boolean this_present_email = true && this.isSetEmail();
    boolean that_present_email = true && that.isSetEmail();
    if (this_present_email || that_present_email) {
      if (!(this_present_email && that_present_email))
        return false;
      if (!this.email.equals(that.email))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetUserID()) ? 131071 : 524287);
    if (isSetUserID())
      hashCode = hashCode * 8191 + userID.hashCode();

    hashCode = hashCode * 8191 + ((isSetUserName()) ? 131071 : 524287);
    if (isSetUserName())
      hashCode = hashCode * 8191 + userName.hashCode();

    hashCode = hashCode * 8191 + ((isSetLoginName()) ? 131071 : 524287);
    if (isSetLoginName())
      hashCode = hashCode * 8191 + loginName.hashCode();

    hashCode = hashCode * 8191 + ((isSetPassword()) ? 131071 : 524287);
    if (isSetPassword())
      hashCode = hashCode * 8191 + password.hashCode();

    hashCode = hashCode * 8191 + ((isSetEmail()) ? 131071 : 524287);
    if (isSetEmail())
      hashCode = hashCode * 8191 + email.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcUser other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetUserID()).compareTo(other.isSetUserID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userID, other.userID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUserName()).compareTo(other.isSetUserName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userName, other.userName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLoginName()).compareTo(other.isSetLoginName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLoginName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.loginName, other.loginName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPassword()).compareTo(other.isSetPassword());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPassword()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.password, other.password);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetEmail()).compareTo(other.isSetEmail());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEmail()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.email, other.email);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcUser(");
    boolean first = true;

    sb.append("userID:");
    if (this.userID == null) {
      sb.append("null");
    } else {
      sb.append(this.userID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("userName:");
    if (this.userName == null) {
      sb.append("null");
    } else {
      sb.append(this.userName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("loginName:");
    if (this.loginName == null) {
      sb.append("null");
    } else {
      sb.append(this.loginName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("password:");
    if (this.password == null) {
      sb.append("null");
    } else {
      sb.append(this.password);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("email:");
    if (this.email == null) {
      sb.append("null");
    } else {
      sb.append(this.email);
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

  private static class RpcUserStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcUserStandardScheme getScheme() {
      return new RpcUserStandardScheme();
    }
  }

  private static class RpcUserStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcUser> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcUser struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // USER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.userID = iprot.readString();
              struct.setUserIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // USER_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.userName = iprot.readString();
              struct.setUserNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LOGIN_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.loginName = iprot.readString();
              struct.setLoginNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PASSWORD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.password = iprot.readString();
              struct.setPasswordIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // EMAIL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.email = iprot.readString();
              struct.setEmailIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcUser struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.userID != null) {
        oprot.writeFieldBegin(USER_ID_FIELD_DESC);
        oprot.writeString(struct.userID);
        oprot.writeFieldEnd();
      }
      if (struct.userName != null) {
        oprot.writeFieldBegin(USER_NAME_FIELD_DESC);
        oprot.writeString(struct.userName);
        oprot.writeFieldEnd();
      }
      if (struct.loginName != null) {
        oprot.writeFieldBegin(LOGIN_NAME_FIELD_DESC);
        oprot.writeString(struct.loginName);
        oprot.writeFieldEnd();
      }
      if (struct.password != null) {
        oprot.writeFieldBegin(PASSWORD_FIELD_DESC);
        oprot.writeString(struct.password);
        oprot.writeFieldEnd();
      }
      if (struct.email != null) {
        oprot.writeFieldBegin(EMAIL_FIELD_DESC);
        oprot.writeString(struct.email);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcUserTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcUserTupleScheme getScheme() {
      return new RpcUserTupleScheme();
    }
  }

  private static class RpcUserTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcUser> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcUser struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetUserID()) {
        optionals.set(0);
      }
      if (struct.isSetUserName()) {
        optionals.set(1);
      }
      if (struct.isSetLoginName()) {
        optionals.set(2);
      }
      if (struct.isSetPassword()) {
        optionals.set(3);
      }
      if (struct.isSetEmail()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetUserID()) {
        oprot.writeString(struct.userID);
      }
      if (struct.isSetUserName()) {
        oprot.writeString(struct.userName);
      }
      if (struct.isSetLoginName()) {
        oprot.writeString(struct.loginName);
      }
      if (struct.isSetPassword()) {
        oprot.writeString(struct.password);
      }
      if (struct.isSetEmail()) {
        oprot.writeString(struct.email);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcUser struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.userID = iprot.readString();
        struct.setUserIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.userName = iprot.readString();
        struct.setUserNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.loginName = iprot.readString();
        struct.setLoginNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.password = iprot.readString();
        struct.setPasswordIsSet(true);
      }
      if (incoming.get(4)) {
        struct.email = iprot.readString();
        struct.setEmailIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

