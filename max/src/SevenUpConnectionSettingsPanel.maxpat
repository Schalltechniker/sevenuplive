{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 157.0, 623.0, 608.0, 502.0 ],
		"bglocked" : 0,
		"defrect" : [ 157.0, 623.0, 608.0, 502.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 0,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 0,
		"gridsize" : [ 4.0, 4.0 ],
		"gridsnaponopen" : 0,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"enablehscroll" : 1,
		"enablevscroll" : 1,
		"devicewidth" : 0.0,
		"boxes" : [ 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "sendbox _parameter_range foo bar bing",
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 252.0, 216.0, 225.0, 18.0 ],
					"id" : "obj-29",
					"fontname" : "Arial",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numoutlets" : 0,
					"patching_rect" : [ 120.0, 388.0, 25.0, 25.0 ],
					"id" : "obj-28",
					"numinlets" : 1,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "OSC Listen Port",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"presentation_rect" : [ 104.0, 82.0, 128.0, 18.0 ],
					"patching_rect" : [ 156.0, 100.0, 102.0, 18.0 ],
					"presentation" : 1,
					"id" : "obj-27",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "OSC Host Port",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"presentation_rect" : [ 89.0, 67.0, 128.0, 18.0 ],
					"patching_rect" : [ 44.0, 100.0, 77.0, 18.0 ],
					"presentation" : 1,
					"id" : "obj-26",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "OSC Host Address",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"presentation_rect" : [ 74.0, 52.0, 127.0, 18.0 ],
					"patching_rect" : [ 112.0, 76.0, 126.0, 18.0 ],
					"presentation" : 1,
					"id" : "obj-25",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "OSC Prefix",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"presentation_rect" : [ 59.0, 37.0, 129.0, 18.0 ],
					"patching_rect" : [ 112.0, 52.0, 127.0, 18.0 ],
					"presentation" : 1,
					"id" : "obj-24",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Monome Virtual Grid Size",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"presentation_rect" : [ 44.0, 22.0, 127.0, 18.0 ],
					"patching_rect" : [ 112.0, 28.0, 125.0, 18.0 ],
					"presentation" : 1,
					"id" : "obj-23",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.numbox",
					"varname" : "live.numbox[1]",
					"numoutlets" : 2,
					"outlettype" : [ "", "float" ],
					"presentation_rect" : [ 52.0, 96.0, 36.0, 15.0 ],
					"patching_rect" : [ 120.0, 100.0, 36.0, 15.0 ],
					"presentation" : 1,
					"id" : "obj-22",
					"parameter_enable" : 1,
					"numinlets" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 9999.0,
							"parameter_mmin" : 1024.0,
							"parameter_initial" : [ 8080 ],
							"parameter_type" : 0,
							"parameter_initial_enable" : 1,
							"parameter_shortname" : "live.numbox",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.numbox[1]",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.numbox",
					"varname" : "live.numbox",
					"numoutlets" : 2,
					"outlettype" : [ "", "float" ],
					"presentation_rect" : [ 8.0, 96.0, 36.0, 15.0 ],
					"patching_rect" : [ 8.0, 100.0, 36.0, 15.0 ],
					"presentation" : 1,
					"id" : "obj-20",
					"parameter_enable" : 1,
					"numinlets" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 9999.0,
							"parameter_mmin" : 1024.0,
							"parameter_initial" : [ 8000 ],
							"parameter_type" : 0,
							"parameter_initial_enable" : 1,
							"parameter_shortname" : "live.numbox",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.numbox",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "textedit",
					"text" : "127.0.0.1",
					"numoutlets" : 4,
					"fontsize" : 12.0,
					"outlettype" : [ "", "int", "", "" ],
					"presentation_rect" : [ 8.0, 72.0, 101.0, 20.0 ],
					"patching_rect" : [ 8.0, 72.0, 101.0, 20.0 ],
					"presentation" : 1,
					"id" : "obj-17",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "textedit",
					"text" : "7up",
					"numoutlets" : 4,
					"fontsize" : 12.0,
					"outlettype" : [ "", "int", "", "" ],
					"presentation_rect" : [ 8.0, 48.0, 101.0, 20.0 ],
					"patching_rect" : [ 8.0, 48.0, 101.0, 20.0 ],
					"presentation" : 1,
					"id" : "obj-16",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.menu",
					"varname" : "live.menu",
					"numoutlets" : 3,
					"outlettype" : [ "", "", "float" ],
					"presentation_rect" : [ 8.0, 28.0, 100.0, 15.0 ],
					"pictures" : [  ],
					"patching_rect" : [ 8.0, 28.0, 100.0, 15.0 ],
					"presentation" : 1,
					"id" : "obj-15",
					"parameter_enable" : 1,
					"numinlets" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "foo", "bar", "bing" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 2,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "live.menu",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.menu",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : ""
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "start/stop connections",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"presentation_rect" : [ 29.0, 7.0, 128.0, 18.0 ],
					"patching_rect" : [ 28.0, 8.0, 126.0, 18.0 ],
					"presentation" : 1,
					"id" : "obj-9",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "shutdown",
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 89.0, 311.0, 62.0, 18.0 ],
					"id" : "obj-7",
					"fontname" : "Arial",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "initialize",
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 26.0, 311.0, 53.0, 18.0 ],
					"id" : "obj-6",
					"fontname" : "Arial",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route 0 1",
					"numoutlets" : 3,
					"fontsize" : 12.0,
					"outlettype" : [ "", "", "" ],
					"patching_rect" : [ 45.0, 255.0, 58.0, 20.0 ],
					"id" : "obj-5",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.toggle",
					"varname" : "live.toggle",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"presentation_rect" : [ 137.0, 139.0, 15.0, 15.0 ],
					"patching_rect" : [ 9.0, 7.0, 15.0, 15.0 ],
					"presentation" : 1,
					"id" : "obj-4",
					"parameter_enable" : 1,
					"numinlets" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "off", "on" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 1.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 2,
							"parameter_initial_enable" : 0,
							"parameter_shortname" : "live.toggle",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.toggle",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : ""
						}

					}

				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-29", 0 ],
					"destination" : [ "obj-15", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 1 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-15" : [ "live.menu", "live.menu", 0 ],
			"obj-22" : [ "live.numbox[1]", "live.numbox", 0 ],
			"obj-20" : [ "live.numbox", "live.numbox", 0 ],
			"obj-4" : [ "live.toggle", "live.toggle", 0 ]
		}

	}

}
