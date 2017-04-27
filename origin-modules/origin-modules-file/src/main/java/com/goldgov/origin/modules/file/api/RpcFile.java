/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.file.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-04-27")
public class RpcFile implements org.apache.thrift.TBase<RpcFile, RpcFile._Fields>, java.io.Serializable, Cloneable, Comparable<RpcFile> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcFile");

  private static final org.apache.thrift.protocol.TField FILE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("fileID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField FILE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("fileName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField FILE_SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("fileSize", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField FILE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("fileType", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField CREATE_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("createDate", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField RELATION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("relationID", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField DOWNLOAD_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("downloadNum", org.apache.thrift.protocol.TType.I64, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcFileStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcFileTupleSchemeFactory();

  private java.lang.String fileID; // required
  private java.lang.String fileName; // required
  private long fileSize; // required
  private java.lang.String fileType; // required
  private long createDate; // required
  private java.lang.String relationID; // required
  private long downloadNum; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FILE_ID((short)1, "fileID"),
    FILE_NAME((short)2, "fileName"),
    FILE_SIZE((short)3, "fileSize"),
    FILE_TYPE((short)4, "fileType"),
    CREATE_DATE((short)5, "createDate"),
    RELATION_ID((short)6, "relationID"),
    DOWNLOAD_NUM((short)7, "downloadNum");

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
        case 1: // FILE_ID
          return FILE_ID;
        case 2: // FILE_NAME
          return FILE_NAME;
        case 3: // FILE_SIZE
          return FILE_SIZE;
        case 4: // FILE_TYPE
          return FILE_TYPE;
        case 5: // CREATE_DATE
          return CREATE_DATE;
        case 6: // RELATION_ID
          return RELATION_ID;
        case 7: // DOWNLOAD_NUM
          return DOWNLOAD_NUM;
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
  private static final int __FILESIZE_ISSET_ID = 0;
  private static final int __CREATEDATE_ISSET_ID = 1;
  private static final int __DOWNLOADNUM_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FILE_ID, new org.apache.thrift.meta_data.FieldMetaData("fileID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FILE_NAME, new org.apache.thrift.meta_data.FieldMetaData("fileName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FILE_SIZE, new org.apache.thrift.meta_data.FieldMetaData("fileSize", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.FILE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("fileType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CREATE_DATE, new org.apache.thrift.meta_data.FieldMetaData("createDate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.RELATION_ID, new org.apache.thrift.meta_data.FieldMetaData("relationID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DOWNLOAD_NUM, new org.apache.thrift.meta_data.FieldMetaData("downloadNum", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcFile.class, metaDataMap);
  }

  public RpcFile() {
  }

  public RpcFile(
    java.lang.String fileID,
    java.lang.String fileName,
    long fileSize,
    java.lang.String fileType,
    long createDate,
    java.lang.String relationID,
    long downloadNum)
  {
    this();
    this.fileID = fileID;
    this.fileName = fileName;
    this.fileSize = fileSize;
    setFileSizeIsSet(true);
    this.fileType = fileType;
    this.createDate = createDate;
    setCreateDateIsSet(true);
    this.relationID = relationID;
    this.downloadNum = downloadNum;
    setDownloadNumIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcFile(RpcFile other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetFileID()) {
      this.fileID = other.fileID;
    }
    if (other.isSetFileName()) {
      this.fileName = other.fileName;
    }
    this.fileSize = other.fileSize;
    if (other.isSetFileType()) {
      this.fileType = other.fileType;
    }
    this.createDate = other.createDate;
    if (other.isSetRelationID()) {
      this.relationID = other.relationID;
    }
    this.downloadNum = other.downloadNum;
  }

  public RpcFile deepCopy() {
    return new RpcFile(this);
  }

  @Override
  public void clear() {
    this.fileID = null;
    this.fileName = null;
    setFileSizeIsSet(false);
    this.fileSize = 0;
    this.fileType = null;
    setCreateDateIsSet(false);
    this.createDate = 0;
    this.relationID = null;
    setDownloadNumIsSet(false);
    this.downloadNum = 0;
  }

  public java.lang.String getFileID() {
    return this.fileID;
  }

  public void setFileID(java.lang.String fileID) {
    this.fileID = fileID;
  }

  public void unsetFileID() {
    this.fileID = null;
  }

  /** Returns true if field fileID is set (has been assigned a value) and false otherwise */
  public boolean isSetFileID() {
    return this.fileID != null;
  }

  public void setFileIDIsSet(boolean value) {
    if (!value) {
      this.fileID = null;
    }
  }

  public java.lang.String getFileName() {
    return this.fileName;
  }

  public void setFileName(java.lang.String fileName) {
    this.fileName = fileName;
  }

  public void unsetFileName() {
    this.fileName = null;
  }

  /** Returns true if field fileName is set (has been assigned a value) and false otherwise */
  public boolean isSetFileName() {
    return this.fileName != null;
  }

  public void setFileNameIsSet(boolean value) {
    if (!value) {
      this.fileName = null;
    }
  }

  public long getFileSize() {
    return this.fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
    setFileSizeIsSet(true);
  }

  public void unsetFileSize() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __FILESIZE_ISSET_ID);
  }

  /** Returns true if field fileSize is set (has been assigned a value) and false otherwise */
  public boolean isSetFileSize() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __FILESIZE_ISSET_ID);
  }

  public void setFileSizeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __FILESIZE_ISSET_ID, value);
  }

  public java.lang.String getFileType() {
    return this.fileType;
  }

  public void setFileType(java.lang.String fileType) {
    this.fileType = fileType;
  }

  public void unsetFileType() {
    this.fileType = null;
  }

  /** Returns true if field fileType is set (has been assigned a value) and false otherwise */
  public boolean isSetFileType() {
    return this.fileType != null;
  }

  public void setFileTypeIsSet(boolean value) {
    if (!value) {
      this.fileType = null;
    }
  }

  public long getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(long createDate) {
    this.createDate = createDate;
    setCreateDateIsSet(true);
  }

  public void unsetCreateDate() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CREATEDATE_ISSET_ID);
  }

  /** Returns true if field createDate is set (has been assigned a value) and false otherwise */
  public boolean isSetCreateDate() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CREATEDATE_ISSET_ID);
  }

  public void setCreateDateIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CREATEDATE_ISSET_ID, value);
  }

  public java.lang.String getRelationID() {
    return this.relationID;
  }

  public void setRelationID(java.lang.String relationID) {
    this.relationID = relationID;
  }

  public void unsetRelationID() {
    this.relationID = null;
  }

  /** Returns true if field relationID is set (has been assigned a value) and false otherwise */
  public boolean isSetRelationID() {
    return this.relationID != null;
  }

  public void setRelationIDIsSet(boolean value) {
    if (!value) {
      this.relationID = null;
    }
  }

  public long getDownloadNum() {
    return this.downloadNum;
  }

  public void setDownloadNum(long downloadNum) {
    this.downloadNum = downloadNum;
    setDownloadNumIsSet(true);
  }

  public void unsetDownloadNum() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __DOWNLOADNUM_ISSET_ID);
  }

  /** Returns true if field downloadNum is set (has been assigned a value) and false otherwise */
  public boolean isSetDownloadNum() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __DOWNLOADNUM_ISSET_ID);
  }

  public void setDownloadNumIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __DOWNLOADNUM_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case FILE_ID:
      if (value == null) {
        unsetFileID();
      } else {
        setFileID((java.lang.String)value);
      }
      break;

    case FILE_NAME:
      if (value == null) {
        unsetFileName();
      } else {
        setFileName((java.lang.String)value);
      }
      break;

    case FILE_SIZE:
      if (value == null) {
        unsetFileSize();
      } else {
        setFileSize((java.lang.Long)value);
      }
      break;

    case FILE_TYPE:
      if (value == null) {
        unsetFileType();
      } else {
        setFileType((java.lang.String)value);
      }
      break;

    case CREATE_DATE:
      if (value == null) {
        unsetCreateDate();
      } else {
        setCreateDate((java.lang.Long)value);
      }
      break;

    case RELATION_ID:
      if (value == null) {
        unsetRelationID();
      } else {
        setRelationID((java.lang.String)value);
      }
      break;

    case DOWNLOAD_NUM:
      if (value == null) {
        unsetDownloadNum();
      } else {
        setDownloadNum((java.lang.Long)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case FILE_ID:
      return getFileID();

    case FILE_NAME:
      return getFileName();

    case FILE_SIZE:
      return getFileSize();

    case FILE_TYPE:
      return getFileType();

    case CREATE_DATE:
      return getCreateDate();

    case RELATION_ID:
      return getRelationID();

    case DOWNLOAD_NUM:
      return getDownloadNum();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case FILE_ID:
      return isSetFileID();
    case FILE_NAME:
      return isSetFileName();
    case FILE_SIZE:
      return isSetFileSize();
    case FILE_TYPE:
      return isSetFileType();
    case CREATE_DATE:
      return isSetCreateDate();
    case RELATION_ID:
      return isSetRelationID();
    case DOWNLOAD_NUM:
      return isSetDownloadNum();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcFile)
      return this.equals((RpcFile)that);
    return false;
  }

  public boolean equals(RpcFile that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_fileID = true && this.isSetFileID();
    boolean that_present_fileID = true && that.isSetFileID();
    if (this_present_fileID || that_present_fileID) {
      if (!(this_present_fileID && that_present_fileID))
        return false;
      if (!this.fileID.equals(that.fileID))
        return false;
    }

    boolean this_present_fileName = true && this.isSetFileName();
    boolean that_present_fileName = true && that.isSetFileName();
    if (this_present_fileName || that_present_fileName) {
      if (!(this_present_fileName && that_present_fileName))
        return false;
      if (!this.fileName.equals(that.fileName))
        return false;
    }

    boolean this_present_fileSize = true;
    boolean that_present_fileSize = true;
    if (this_present_fileSize || that_present_fileSize) {
      if (!(this_present_fileSize && that_present_fileSize))
        return false;
      if (this.fileSize != that.fileSize)
        return false;
    }

    boolean this_present_fileType = true && this.isSetFileType();
    boolean that_present_fileType = true && that.isSetFileType();
    if (this_present_fileType || that_present_fileType) {
      if (!(this_present_fileType && that_present_fileType))
        return false;
      if (!this.fileType.equals(that.fileType))
        return false;
    }

    boolean this_present_createDate = true;
    boolean that_present_createDate = true;
    if (this_present_createDate || that_present_createDate) {
      if (!(this_present_createDate && that_present_createDate))
        return false;
      if (this.createDate != that.createDate)
        return false;
    }

    boolean this_present_relationID = true && this.isSetRelationID();
    boolean that_present_relationID = true && that.isSetRelationID();
    if (this_present_relationID || that_present_relationID) {
      if (!(this_present_relationID && that_present_relationID))
        return false;
      if (!this.relationID.equals(that.relationID))
        return false;
    }

    boolean this_present_downloadNum = true;
    boolean that_present_downloadNum = true;
    if (this_present_downloadNum || that_present_downloadNum) {
      if (!(this_present_downloadNum && that_present_downloadNum))
        return false;
      if (this.downloadNum != that.downloadNum)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetFileID()) ? 131071 : 524287);
    if (isSetFileID())
      hashCode = hashCode * 8191 + fileID.hashCode();

    hashCode = hashCode * 8191 + ((isSetFileName()) ? 131071 : 524287);
    if (isSetFileName())
      hashCode = hashCode * 8191 + fileName.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(fileSize);

    hashCode = hashCode * 8191 + ((isSetFileType()) ? 131071 : 524287);
    if (isSetFileType())
      hashCode = hashCode * 8191 + fileType.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(createDate);

    hashCode = hashCode * 8191 + ((isSetRelationID()) ? 131071 : 524287);
    if (isSetRelationID())
      hashCode = hashCode * 8191 + relationID.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(downloadNum);

    return hashCode;
  }

  @Override
  public int compareTo(RpcFile other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetFileID()).compareTo(other.isSetFileID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFileID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fileID, other.fileID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFileName()).compareTo(other.isSetFileName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFileName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fileName, other.fileName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFileSize()).compareTo(other.isSetFileSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFileSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fileSize, other.fileSize);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFileType()).compareTo(other.isSetFileType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFileType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fileType, other.fileType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCreateDate()).compareTo(other.isSetCreateDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreateDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.createDate, other.createDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRelationID()).compareTo(other.isSetRelationID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRelationID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.relationID, other.relationID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDownloadNum()).compareTo(other.isSetDownloadNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDownloadNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.downloadNum, other.downloadNum);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcFile(");
    boolean first = true;

    sb.append("fileID:");
    if (this.fileID == null) {
      sb.append("null");
    } else {
      sb.append(this.fileID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("fileName:");
    if (this.fileName == null) {
      sb.append("null");
    } else {
      sb.append(this.fileName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("fileSize:");
    sb.append(this.fileSize);
    first = false;
    if (!first) sb.append(", ");
    sb.append("fileType:");
    if (this.fileType == null) {
      sb.append("null");
    } else {
      sb.append(this.fileType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("createDate:");
    sb.append(this.createDate);
    first = false;
    if (!first) sb.append(", ");
    sb.append("relationID:");
    if (this.relationID == null) {
      sb.append("null");
    } else {
      sb.append(this.relationID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("downloadNum:");
    sb.append(this.downloadNum);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RpcFileStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcFileStandardScheme getScheme() {
      return new RpcFileStandardScheme();
    }
  }

  private static class RpcFileStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcFile> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcFile struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FILE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fileID = iprot.readString();
              struct.setFileIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // FILE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fileName = iprot.readString();
              struct.setFileNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // FILE_SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.fileSize = iprot.readI64();
              struct.setFileSizeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // FILE_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fileType = iprot.readString();
              struct.setFileTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // CREATE_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.createDate = iprot.readI64();
              struct.setCreateDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // RELATION_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.relationID = iprot.readString();
              struct.setRelationIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // DOWNLOAD_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.downloadNum = iprot.readI64();
              struct.setDownloadNumIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcFile struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.fileID != null) {
        oprot.writeFieldBegin(FILE_ID_FIELD_DESC);
        oprot.writeString(struct.fileID);
        oprot.writeFieldEnd();
      }
      if (struct.fileName != null) {
        oprot.writeFieldBegin(FILE_NAME_FIELD_DESC);
        oprot.writeString(struct.fileName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(FILE_SIZE_FIELD_DESC);
      oprot.writeI64(struct.fileSize);
      oprot.writeFieldEnd();
      if (struct.fileType != null) {
        oprot.writeFieldBegin(FILE_TYPE_FIELD_DESC);
        oprot.writeString(struct.fileType);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(CREATE_DATE_FIELD_DESC);
      oprot.writeI64(struct.createDate);
      oprot.writeFieldEnd();
      if (struct.relationID != null) {
        oprot.writeFieldBegin(RELATION_ID_FIELD_DESC);
        oprot.writeString(struct.relationID);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(DOWNLOAD_NUM_FIELD_DESC);
      oprot.writeI64(struct.downloadNum);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcFileTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcFileTupleScheme getScheme() {
      return new RpcFileTupleScheme();
    }
  }

  private static class RpcFileTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcFile> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcFile struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetFileID()) {
        optionals.set(0);
      }
      if (struct.isSetFileName()) {
        optionals.set(1);
      }
      if (struct.isSetFileSize()) {
        optionals.set(2);
      }
      if (struct.isSetFileType()) {
        optionals.set(3);
      }
      if (struct.isSetCreateDate()) {
        optionals.set(4);
      }
      if (struct.isSetRelationID()) {
        optionals.set(5);
      }
      if (struct.isSetDownloadNum()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetFileID()) {
        oprot.writeString(struct.fileID);
      }
      if (struct.isSetFileName()) {
        oprot.writeString(struct.fileName);
      }
      if (struct.isSetFileSize()) {
        oprot.writeI64(struct.fileSize);
      }
      if (struct.isSetFileType()) {
        oprot.writeString(struct.fileType);
      }
      if (struct.isSetCreateDate()) {
        oprot.writeI64(struct.createDate);
      }
      if (struct.isSetRelationID()) {
        oprot.writeString(struct.relationID);
      }
      if (struct.isSetDownloadNum()) {
        oprot.writeI64(struct.downloadNum);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcFile struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.fileID = iprot.readString();
        struct.setFileIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.fileName = iprot.readString();
        struct.setFileNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.fileSize = iprot.readI64();
        struct.setFileSizeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.fileType = iprot.readString();
        struct.setFileTypeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.createDate = iprot.readI64();
        struct.setCreateDateIsSet(true);
      }
      if (incoming.get(5)) {
        struct.relationID = iprot.readString();
        struct.setRelationIDIsSet(true);
      }
      if (incoming.get(6)) {
        struct.downloadNum = iprot.readI64();
        struct.setDownloadNumIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

