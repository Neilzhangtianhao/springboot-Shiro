package com.example.shiro.model;

import org.springframework.http.HttpStatus;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangTianhao
 * @date 2019/12/9 13:14
 */
public class R extends ConcurrentHashMap<String, Object> {
	public R() {
		this.put("code", HttpStatus.OK.value());
		this.put("msg", "success !!!");
	}

	public static R ok() {
		return new R();
	}

	public static R ok(String msg) {
		return R.ok().put("msg", msg);
	}

	public static R ok(Object data) {
		return R.ok().put("data", data);
	}

	public static R ok(String msg, Object data) {
		return R.ok(msg).put("data", data);
	}

	public static R error(HttpStatus status, String error) {
		return R.ok().put("code", status).put("error", error).put("msg", "");
	}

	public static R error(HttpStatus status, String error, String msg) {
		return R.ok().put("code", status).put("error", error).put("msg", msg);
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
