<div class="page-content row">
    <!-- Page header -->
    <div class="page-header">
      <div class="page-title">
        <h3> {{ $pageTitle }} <small>{{ $pageNote }}</small></h3>
      </div>
      <ul class="breadcrumb">
        <li><a href="{{ URL::to('dashboard') }}">{{ Lang::get('core.home') }}</a></li>
		<li><a href="{{ URL::to('estates?md='.$masterdetail["filtermd"]) }}">{{ $pageTitle }}</a></li>
        <li class="active"> {{ Lang::get('core.detail') }} </li>
      </ul>
	 </div>  
	 
	 
 	<div class="page-content-wrapper">   
	   <div class="toolbar-line">
	   		<a href="{{ URL::to('estates?md='.$masterdetail["filtermd"].$trackUri) }}" class="tips btn btn-xs btn-default" title="{{ Lang::get('core.btn_back') }}"><i class="icon-table"></i>&nbsp;{{ Lang::get('core.btn_back') }}</a>
			@if($access['is_add'] ==1)
	   		<a href="{{ URL::to('estates/add/'.$id.'?md='.$masterdetail["filtermd"].$trackUri) }}" class="tips btn btn-xs btn-primary" title="{{ Lang::get('core.btn_edit') }}"><i class="icon-pencil3"></i>&nbsp;{{ Lang::get('core.btn_edit') }}</a>
			@endif  		   	  
		</div>
	<div class="table-responsive">
	<table class="table table-striped table-bordered" >
		<tbody>	
	
					<tr>
						<td width='30%' class='label-view text-right'>Id</td>
						<td>{{ $row->id }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>楼盘名称</td>
						<td>{{ $row->name }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>参考均价</td>
						<td>{{ $row->avg_price }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>开盘时间</td>
						<td>{{ $row->open_time }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>开发商</td>
						<td>{{ $row->developer }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>地址</td>
						<td>{{ $row->address }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>售楼处电话</td>
						<td>{{ $row->sale_phone }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>售楼处地址</td>
						<td>{{ $row->sale_address }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>物业公司</td>
						<td>{{ $row->property }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>建筑类型</td>
						<td>{{ $row->building_type }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>建筑面积</td>
						<td>{{ $row->building_area }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>绿化率</td>
						<td>{{ $row->greening_rate }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>楼盘特色</td>
						<td>{{ $row->feature }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>规划面积</td>
						<td>{{ $row->planning_area }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>建筑装修</td>
						<td>{{ $row->decoration }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>容积率</td>
						<td>{{ $row->volume_ratio }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>车位数</td>
						<td>{{ $row->parking }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>物业类型</td>
						<td>{{ $row->property_type }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>规划户数</td>
						<td>{{ $row->household }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>是否显示在前台</td>
						<td>{{ $row->active }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>所属城市</td>
						<td>{{ SiteHelpers::gridDisplayView($row->city_id,'city_id','1:cities:id:name') }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>Entry By</td>
						<td>{{ $row->entry_by }} </td>
						
					</tr>
				
					<tr>
						<td width='30%' class='label-view text-right'>Loc Id</td>
						<td>{{ $row->loc_id }} </td>
						
					</tr>
				
		</tbody>	
	</table>    
	</div>
	</div>
</div>
	  