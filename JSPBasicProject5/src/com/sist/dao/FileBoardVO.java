package com.sist.dao;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FileBoardVO {
	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private Date regdate;
	private int hit;
	private String filename;
	private int filesize;
	private int rownum;

}
