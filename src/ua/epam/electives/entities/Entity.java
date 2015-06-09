package ua.epam.electives.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    Integer id;

    public Entity() {
    }

    public Entity(Integer id) {
	this.id = id;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public abstract void setValues(Object... obts);

    public abstract Object[] getFieldsValue();

    public static class TableInfo<E extends Entity> {
	private Class<E> cl;
	String tableName;
	String[] tableVieldsName;

	@SuppressWarnings("unchecked")
	public TableInfo(Class<?> cl) {
	    this.cl = (Class<E>) cl;
	}

	public Class<E> getEntityClass() {
	    return cl;
	}

	public String getTableName() {
	    if (tableName == null) {
		tableName = cl.getSimpleName();
	    }
	    return tableName;
	}

	public String[] getTableFieldsName() {
	    if (tableVieldsName == null) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < cl.getDeclaredFields().length; ++i) {
		    list.add(cl.getDeclaredFields()[i].getName());
		}
		tableVieldsName = (String[]) getTableFields(list,
			cl.getSuperclass()).toArray(new String[list.size()]);
	    }
	    return tableVieldsName;
	}

	private List<String> getTableFields(List<String> list, Class<?> c) {
	    if (c != null) {
		ArrayList<String> listL = new ArrayList<>();
		for (int i = 0; i < c.getDeclaredFields().length; ++i) {
		    if (!list.contains(c.getDeclaredFields()[i].getName())) {
			listL.add(c.getDeclaredFields()[i].getName());
		    }
		}
		listL.addAll(list);
		list = getTableFields(listL, c.getSuperclass());
	    }
	    return list;
	}
    }
}
