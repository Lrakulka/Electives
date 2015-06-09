package ua.epam.electives.entities;

public class Contract extends Entity {
    private String comment;
    private Integer mark;
    private Integer idCourse;
    private Integer idStudent;
    private Short finishedPercent;

    public Contract() {
    }

    public Contract(Integer id, String comment, Integer mark, Integer idCourse,
	    Integer idStudent, Short finished) {
	this.id = id;
	this.comment = comment;
	this.mark = mark;
	this.idCourse = idCourse;
	this.idStudent = idStudent;
	this.finishedPercent = finished;
    }

    public Contract(Contract contr) {
	this.id = contr.id;
	this.comment = contr.comment;
	this.mark = contr.mark;
	this.idCourse = contr.idCourse;
	this.idStudent = contr.idStudent;
	this.finishedPercent = contr.finishedPercent;
    }

    public static class ContractTableInfo extends TableInfo<Contract> {

	public ContractTableInfo() {
	    super(Contract.class);
	}
    }

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * @return the comment
     */
    public String getComment() {
	return comment;
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(String comment) {
	this.comment = comment;
    }

    /**
     * @return the mark
     */
    public Integer getMark() {
	return mark;
    }

    /**
     * @param mark
     *            the mark to set
     */
    public void setMark(Integer mark) {
	if (mark != null) {
	    if (mark > 12) {
		mark = 12;
	    }
	    if (mark < -1) {
		mark = 0;
	    }
	    if (finishedPercent != 100) {
		mark = null;
	    }
	}
	this.mark = mark;
    }

    /**
     * @return the idCourse
     */
    public Integer getIdCourse() {
	return idCourse;
    }

    /**
     * @param idCourse
     *            the idCourse to set
     */
    public void setIdCourse(Integer idCourse) {
	this.idCourse = idCourse;
    }

    /**
     * @return the idStudent
     */
    public Integer getIdStudent() {
	return idStudent;
    }

    /**
     * @param idStudent
     *            the idStudent to set
     */
    public void setIdStudent(Integer idStudent) {
	this.idStudent = idStudent;
    }

    public boolean isFinished() {
	return finishedPercent == 100 ? true : false;
    }

    /**
     * @return the finished
     */
    public Short getFinishedPercent() {
	return finishedPercent;
    }

    /**
     * @param finished
     *            the finished to set
     */
    public void setFinishedPercent(Short finished) {
	if (finished > 100) {
	    finished = 100;
	}
	if (finished < 0) {
	    finished = 0;
	}
	this.finishedPercent = finished;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((comment == null) ? 0 : comment.hashCode());
	result = prime * result
		+ ((finishedPercent == null) ? 0 : finishedPercent.hashCode());
	result = prime * result
		+ ((idCourse == null) ? 0 : idCourse.hashCode());
	result = prime * result
		+ ((idStudent == null) ? 0 : idStudent.hashCode());
	result = prime * result + ((mark == null) ? 0 : mark.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Contract other = (Contract) obj;
	if (comment == null) {
	    if (other.comment != null)
		return false;
	} else if (!comment.equals(other.comment))
	    return false;
	if (finishedPercent == null) {
	    if (other.finishedPercent != null)
		return false;
	} else if (!finishedPercent.equals(other.finishedPercent))
	    return false;
	if (idCourse == null) {
	    if (other.idCourse != null)
		return false;
	} else if (!idCourse.equals(other.idCourse))
	    return false;
	if (idStudent == null) {
	    if (other.idStudent != null)
		return false;
	} else if (!idStudent.equals(other.idStudent))
	    return false;
	if (mark == null) {
	    if (other.mark != null)
		return false;
	} else if (!mark.equals(other.mark))
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Contract [id=" + id + ", comment=" + comment + ", mark=" + mark
		+ ", idCourse=" + idCourse + ", idStudent=" + idStudent
		+ ", finished=" + finishedPercent + "]";
    }

    @Override
    public void setValues(Object... obts) {
	this.id = obts[0] != null ? ((Long) obts[0]).intValue() : null;
	this.comment = (String) obts[1];
	this.mark = obts[2] != null ? ((Long) obts[2]).intValue() : null;
	this.idCourse = obts[3] != null ? ((Long) obts[3]).intValue() : null;
	this.idStudent = obts[4] != null ? ((Long) obts[4]).intValue() : null;
	this.finishedPercent = ((Integer) obts[5]).shortValue();
    }

    @Override
    public Object[] getFieldsValue() {
	// Fourth field(isLecturer) is not used in table
	Object[] values = new Object[6];
	values[0] = this.id;
	values[1] = this.comment;
	values[2] = this.mark;
	values[3] = this.idCourse;
	values[4] = this.idStudent;
	values[5] = this.finishedPercent;
	return values;
    }
}
