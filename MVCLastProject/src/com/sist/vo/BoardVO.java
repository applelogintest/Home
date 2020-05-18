package com.sist.vo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int no;
	private String name;
	private String subject;
	private String pwd;
	private Date regdate;
	private String content;
	private int hit;
	private String dbday;
	private int group_id;
	private int group_step;
	private int group_tab;
	private int root;
	private int depth;
	private String filename;
	private String filesize;
	private int type;
	
}
