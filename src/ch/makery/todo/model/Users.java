package ch.makery.todo.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "Users")
@XmlAccessorType (XmlAccessType.FIELD)
public class Users
{
  @XmlElement(name = "user")
  private List<user> users = null;

  public List<user> getUsers() {
    return users;
  }

  public void setUsers(List<user> users) {
    this.users = users;
  }
}
