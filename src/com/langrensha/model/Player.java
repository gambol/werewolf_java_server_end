package com.langrensha.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Formatter;

import com.google.gson.annotations.Expose;
import com.langrensha.utility.Message;


public class Player {
	@Expose
	private int id;
	@Expose
	private String name;
	@Expose
	private Role role;
	@Expose
	private boolean isOwner;

	private Socket socket; // 用户的socket
	// private Scanner input; // 输入 input from client

	private Formatter output; // 输出 output to client

	private BufferedReader input;

	public void output(String str) {
		output.format("%s\n", str);
//		System.out.println("JSON之前：" + str);
		output.flush();
	}

	public void output(Message msg) {
		String text = msg.toJson();
//		System.out.println("JSON之后：" + text);
		output.format("%s\n", text);
		output.flush();
//		System.out.println("玩家" + id + "发送:" + text);
	}

	public String input() {
		String text = "";
		try {
			while ((text = input.readLine()) != null) {
				break;
			}
//			System.out.println("完整收到：玩家" + id + "--" + text);
		} catch (IOException e) {
			System.out.println("玩家" + id + "超时");
		}
		return text;
	}

	public void setSocket(Socket socket) {

		this.socket = socket;
		try {
			this.input = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			
			this.output = new Formatter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Player() {

	}

	public Player(int id, String name) {
		this.id = id;
		this.name = name;

	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}



	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//
//	public String toString() {
//		if (role != null)
//			return String
//					.format("id:%2d, name:%s, roleName:%s, loverId:%2d, status:%d, voteList:%s %s",
//							id, name, role.getName(), role.getLoverId(),
//							role.getStatus(), role.getVoteList().toString(),
//							role.isSheriff() ? ",是" : "");
//		else
//			return String.format("id:%2d, name:%s,isOwner:%s", id, name,
//					isOwner);
//	}

}
