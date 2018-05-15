<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集控后台首页</title>
<link href="../../css/public/index.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/main.css" rel="stylesheet" type="text/css" />
<script src="../../js/public/jquery.min.1.10.1.js"></script>
<script src="../../js/public/public.js"></script>
</head>
<body>
	<div class="all" style="display: none;">
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
		<input type="checkbox" />
	</div>
<div class="public">
<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
	<tr>
		<td width="100%" height="100%" colspan="2">
			<div class="public_head">
				<div class="public_head_logo"></div>
				<div class=" public_adminout">
					<a href="#" class="public_admin" style="color: #fff;">您好，amdin</a>
					<a href="#" style="color: #fff;">退出</a>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width="260px" style="float:left;">
			<div class="public_left">
				<div class="tree_title tree_title_close ">
					<a href="#" class="tree_titlea zz">权限管理</a>
				</div>
				<div class="tree_title tree_title_close">
					<a href="#" class="tree_titlea">角色管理</a>
				</div>
				<div class="tree_title tree_title_close">
					<a href="#"  class="tree_titlea" >设备管理</a>
					<div class="tree_content">
						<a href="#">设备类型管理</a>
						<a href="#">设备型号管理</a>
						<a href="#">设备命令管理</a>
				</div>
				</div>
				<div class="tree_title tree_title_close">
					<a href="#"  class="tree_titlea" >主机管理</a>
					<div class="tree_content">
						<a href="#">主机命令管理</a>
					</div>
				</div>
				
			</div>
		</td>
		<td align="center"  background="#f2f2f2" width="100%" height="100%">
			<div class="public_right">
			<div class="public_right_center">
				<div class="amd">
					<a href="#" class="amd_avarage">添加</a>
					<a href="#" class="amd_avarage">修改</a>
					<a href="#" class="amd_avarage">删除</a>
				</div>
				<div class="check_all">
				<table class="table" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_head">
								<tr>
									<td width="5%" align="center"></td>
									<td width="5%" align="center">
										<div class="checkall_head">
											<input id="check_head" type="checkbox" />
											<div  class="bg" id="checkallhead" onclick="checkall()"></div>
										</div>
									</td>
									<td width="16%" align="center">命令名称</td>
									<td width="16%" align="center">命令分组</td>
									<td width="16%" align="center">命令值</td>
									<td width="16%" align="center">默认缺省</td>
									<td width="16%" align="center">命令图标</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">1</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">开机</td>
									<td width="16%" align="center">电源管理</td>
									<td width="16%" align="center">0x001001</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">2</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">关机</td>
									<td width="16%" align="center">电源管理</td>
									<td width="16%" align="center">0x001002</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">3</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">复位</td>
									<td width="16%" align="center">电源管理</td>
									<td width="16%" align="center">0x001003</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">4</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">预置点设置</td>
									<td width="16%" align="center">PTZ控制</td>
									<td width="16%" align="center">0x002001</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">5</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">预置点定位</td>
									<td width="16%" align="center">PTZ控制</td>
									<td width="16%" align="center">0x002002</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">6</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">水平转动</td>
									<td width="16%" align="center">PTZ控制</td>
									<td width="16%" align="center">0x002003</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">7</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">垂直转动</td>
									<td width="16%" align="center">PTZ控制</td>
									<td width="16%" align="center">0x002004</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">8</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">巡航</td>
									<td width="16%" align="center">PTZ控制</td>
									<td width="16%" align="center">0x002005</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">9</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">辅助灯光控制</td>
									<td width="16%" align="center">PTZ控制</td>
									<td width="16%" align="center">0x002006</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">10</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">镜头聚焦</td>
									<td width="16%" align="center">镜头控制</td>
									<td width="16%" align="center">0x003001</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">11</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">镜头变倍</td>
									<td width="16%" align="center">镜头控制</td>
									<td width="16%" align="center">0x003002</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">12</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">光圈设置</td>
									<td width="16%" align="center">镜头控制</td>
									<td width="16%" align="center">0x003003</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">13</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">创建直播</td>
									<td width="16%" align="center">直播管理</td>
									<td width="16%" align="center">0x004001</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">14</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">删除直播</td>
									<td width="16%" align="center">直播管理</td>
									<td width="16%" align="center">0x004002</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">15</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">修改直播</td>
									<td width="16%" align="center">直播管理</td>
									<td width="16%" align="center">0x004003</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">16</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">开始直播</td>
									<td width="16%" align="center">直播管理</td>
									<td width="16%" align="center">0x004004</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">17</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">暂停直播</td>
									<td width="16%" align="center">直播管理</td>
									<td width="16%" align="center">0x004005</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">18</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">停止直播</td>
									<td width="16%" align="center">直播管理</td>
									<td width="16%" align="center">0x004006</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">19</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">创建录播</td>
									<td width="16%" align="center">录播管理</td>
									<td width="16%" align="center">0x005001</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">20</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">删除录播</td>
									<td width="16%" align="center">录播管理</td>
									<td width="16%" align="center">0x005002</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">21</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">修改录播</td>
									<td width="16%" align="center">录播管理</td>
									<td width="16%" align="center">0x005003</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">22</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">开始录播</td>
									<td width="16%" align="center">录播管理</td>
									<td width="16%" align="center">0x005004</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
								<tr>
									<td width="5%" align="center">23</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">暂停录播</td>
									<td width="16%" align="center">录播管理</td>
									<td width="16%" align="center">0x005005</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr class="tr">
						<td>
							<table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
								<tr>
									<td width="5%" align="center">24</td>
									<td width="5%" align="center">
										<div class="check_head">
											<div class="bg"></div>
										</div>
									</td>
									<td width="16%" align="center">停止录播</td>
									<td width="16%" align="center">录播管理</td>
									<td width="16%" align="center">0x005006</td>
									<td width="16%" align="center">0</td>
									<td width="16%" align="center"><img src=""/></td>
								</tr>
							</table>
						</td>
					</tr>
					<!--<tr>
						<table border="0" cellpadding="0" cellspacing="0" class="table_recycle">
							<tr>
								<td colspan="5" align="center">
									<span class="page_prev">上一页</span>
									<span class="page_center">1/10</span>
									<span class="page_next">下一页</span>
								</td>
							</tr>
						</table>
					</tr>-->
				</table>
				
			</div>	
			</div>
		</div>
		</td>
	</tr>
	<tr>
		<td colspan="2" width="100%">
			<div class="foot">
				<div class="foot_center">鸿合科技版权所有</div>
				<div class="foot_center">鸿合科技版权所有</div>
			</div>
		</td>
	</tr>
</table>
</div>  
</body>
</html>
