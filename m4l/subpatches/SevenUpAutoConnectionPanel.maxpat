{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 88.0, 140.0, 1163.0, 1068.0 ],
		"bglocked" : 0,
		"defrect" : [ 88.0, 140.0, 1163.0, 1068.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 1,
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
					"maxclass" : "live.numbox",
					"varname" : "sevenup-osc-autoconf-listen-port",
					"numinlets" : 1,
					"presentation_rect" : [ 342.0, 4.0, 36.0, 15.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 747.0, 489.0, 36.0, 15.0 ],
					"presentation" : 1,
					"outlettype" : [ "", "float" ],
					"id" : "obj-74",
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.numbox",
							"parameter_invisible" : 1,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "sevenup-osc-autoconf-listen-port",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 9999.0,
							"parameter_mmin" : 1024.0,
							"parameter_initial" : [ 8007.0 ],
							"parameter_type" : 0,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 609.0, 456.0, 60.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "bang" ],
					"id" : "obj-71"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.text",
					"hint" : "Autoconfigure using oscbonjour",
					"varname" : "live.text",
					"activebgoncolor" : [ 0.94902, 0.635294, 0.054902, 1.0 ],
					"automation" : "OFF",
					"automationon" : "ON",
					"numinlets" : 1,
					"presentation_rect" : [ 237.0, 3.97015, 103.0, 15.059702 ],
					"text" : "AUTOCONFIG/OFF",
					"numoutlets" : 2,
					"patching_rect" : [ 610.0, 488.0, 134.0, 16.0 ],
					"presentation" : 1,
					"outlettype" : [ "", "" ],
					"texton" : "AUTOCONFIG/ON",
					"id" : "obj-70",
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.text",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "Autoconf",
							"parameter_longname" : "live.text",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "OFF", "ON" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 1.0,
							"parameter_mmin" : 0.0,
							"parameter_initial" : [ 0.0 ],
							"parameter_type" : 2,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 952.0, 826.0, 24.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "bang" ],
					"id" : "obj-69"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 774.0, 826.0, 24.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "bang" ],
					"id" : "obj-68"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-osc-listen-port",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 776.0, 566.0, 162.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-66"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-osc-host-port",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 649.0, 589.0, 157.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-67"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"name" : "autoconfig.maxpat",
					"numinlets" : 2,
					"presentation_rect" : [ 235.0, 22.0, 144.0, 22.0 ],
					"args" : [  ],
					"numoutlets" : 4,
					"patching_rect" : [ 610.0, 536.0, 204.0, 23.0 ],
					"presentation" : 1,
					"outlettype" : [ "", "", "", "" ],
					"id" : "obj-65"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Use only 1 Instance of SevenUpCore in a set.",
					"linecount" : 2,
					"fontsize" : 9.0,
					"numinlets" : 1,
					"presentation_rect" : [ 10.0, 112.0, 232.0, 17.0 ],
					"numoutlets" : 0,
					"patching_rect" : [ 73.0, 547.0, 115.0, 27.0 ],
					"fontname" : "Arial",
					"presentation" : 1,
					"id" : "obj-64"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "closebang",
					"fontsize" : 12.0,
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 65.0, 14.0, 65.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "bang" ],
					"id" : "obj-61"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 5.0, 39.0, 32.5, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-56"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "freebang",
					"fontsize" : 12.0,
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 5.0, 14.0, 58.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "bang" ],
					"id" : "obj-20"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-osc-listen-port",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 256.0, 361.0, 162.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-60"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-osc-listen-port",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 638.0, 375.0, 162.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-59"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-osc-host-port",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 256.0, 335.0, 157.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-58"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-osc-host-port",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 639.0, 348.0, 157.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-57"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-monome-ctrl",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 255.0, 312.0, 153.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-53"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-monome-ctrl",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 641.0, 323.0, 153.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-48"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-address-ctrl",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 256.0, 289.0, 149.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-55"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pvar sevenup-prefix-ctrl",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 257.0, 267.0, 136.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-54"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "sevenup-address-ctrl[1]",
					"text" : "pattr sevenup-address-ctrl",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 3,
					"patching_rect" : [ 406.0, 792.0, 150.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-52",
					"saved_object_attributes" : 					{
						"initial" : [ "127.0.0.1" ],
						"parameter_enable" : 1
					}
,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "sevenup-prefix-ctrl[2]",
							"parameter_invisible" : 1,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "sevenup-address-ctrl[1]",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_initial" : [ "127.0.0.1" ],
							"parameter_type" : 3,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "sevenup-prefix-ctrl[1]",
					"text" : "pattr sevenup-prefix-ctrl",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 3,
					"patching_rect" : [ 228.0, 760.0, 137.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-51",
					"saved_object_attributes" : 					{
						"initial" : [ "7up" ],
						"parameter_enable" : 1
					}
,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "sevenup-prefix-ctrl[1]",
							"parameter_invisible" : 1,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "sevenup-prefix-ctrl[1]",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_initial" : [ "7up" ],
							"parameter_type" : 3,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 207.0, 497.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"id" : "obj-63"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "SevenUpLogoAnimate",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 169.5, 541.5, 131.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-62"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 145.0, 497.0, 60.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "bang" ],
					"id" : "obj-50"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route startstop",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 2,
					"patching_rect" : [ 163.0, 43.0, 87.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "", "" ],
					"id" : "obj-49"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 490.0, 2.0, 25.0, 25.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-47",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route monomes",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 2,
					"patching_rect" : [ 488.0, 41.0, 94.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "", "" ],
					"id" : "obj-45"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend _parameter_range",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 600.0, 76.0, 157.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-46"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numinlets" : 0,
					"numoutlets" : 1,
					"patching_rect" : [ 164.0, 4.0, 25.0, 25.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-44",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.grid",
					"varname" : "live.grid",
					"directioncolor" : [ 1.0, 1.0, 1.0, 1.0 ],
					"bordercolor2" : [ 0.537255, 0.537255, 0.537255, 1.0 ],
					"direction" : 0,
					"bgstepcolor2" : [ 1.0, 1.0, 1.0, 1.0 ],
					"numinlets" : 2,
					"presentation_rect" : [ 288.0, 44.828743, 90.373787, 83.146133 ],
					"bordercolor" : [ 0.537255, 0.537255, 0.537255, 1.0 ],
					"matrixmode" : 1,
					"columns" : 8,
					"numoutlets" : 6,
					"rows" : 8,
					"patching_rect" : [ 190.0, 586.0, 102.373787, 105.974876 ],
					"presentation" : 1,
					"outlettype" : [ "", "", "", "", "", "" ],
					"id" : "obj-37",
					"bgstepcolor" : [ 1.0, 1.0, 1.0, 1.0 ],
					"parameter_enable" : 1,
					"ignoreclick" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.grid",
							"parameter_invisible" : 1,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.grid",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_initial" : [ 3, 8, 8, 0, 24, 1006, 1007, 2000, 2001, 2002, 2003, 2006, 2007, 3000, 3001, 3002, 3003, 3004, 3006, 3007, 4004, 4005, 4006, 4007, 5005, 5006, 5007, 6006, 6007, 2, 2, 2, 2, 2, 2, 2, 2 ],
							"parameter_type" : 3,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "TABS textcolor 0 0 0 1",
					"fontsize" : 10.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 465.0, 434.0, 116.0, 16.0 ],
					"fontname" : "Arial Bold",
					"outlettype" : [ "" ],
					"id" : "obj-33"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "TABS textcolor 0.27 0.33 0.4 1",
					"fontsize" : 10.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 588.0, 432.0, 152.0, 16.0 ],
					"fontname" : "Arial Bold",
					"outlettype" : [ "" ],
					"id" : "obj-35"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "TABS ignoreclick 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 550.0, 405.0, 112.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-32"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "TABS ignoreclick 0",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 433.0, 405.0, 112.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-31"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 473.0, 501.0, 25.0, 25.0 ],
					"id" : "obj-30",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "readonly 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 235.0, 236.0, 67.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-43"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "readonly 0",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 305.0, 238.0, 67.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-42"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 432.0, 186.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"id" : "obj-39"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "ignoreclick 0",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 871.0, 246.0, 77.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-40"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "active 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 800.0, 258.0, 52.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-41"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 7.0, 149.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"id" : "obj-38"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "ignoreclick 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 485.0, 258.0, 77.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-36"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "active 0",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 417.0, 257.0, 52.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-34"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl slice 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 2,
					"patching_rect" : [ 285.0, 810.0, 57.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "", "" ],
					"id" : "obj-29"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend oscprefix",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 286.0, 833.0, 106.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-21"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl slice 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 2,
					"patching_rect" : [ 447.0, 848.0, 57.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "", "" ],
					"id" : "obj-22"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "abs",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 943.0, 880.0, 30.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "int" ],
					"id" : "obj-19"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "abs",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 764.0, 885.0, 30.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "int" ],
					"id" : "obj-18"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.numbox",
					"varname" : "sevenup-osc-listen-port",
					"numinlets" : 1,
					"presentation_rect" : [ 119.0, 92.0, 36.0, 15.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 974.0, 800.0, 36.0, 15.0 ],
					"presentation" : 1,
					"outlettype" : [ "", "float" ],
					"id" : "obj-14",
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.numbox",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "sevenup-osc-listen-port",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 9999.0,
							"parameter_mmin" : 1024.0,
							"parameter_initial" : [ 8000.0 ],
							"parameter_type" : 0,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.numbox",
					"varname" : "sevenup-osc-host-port",
					"numinlets" : 1,
					"presentation_rect" : [ 9.0, 92.0, 36.0, 15.0 ],
					"numoutlets" : 2,
					"patching_rect" : [ 794.0, 800.0, 36.0, 15.0 ],
					"presentation" : 1,
					"outlettype" : [ "", "float" ],
					"id" : "obj-13",
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.numbox",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "sevenup-osc-host-port",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 0,
							"parameter_mmax" : 9999.0,
							"parameter_mmin" : 1024.0,
							"parameter_initial" : [ 8080.0 ],
							"parameter_type" : 0,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend hostaddress",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 433.0, 876.0, 123.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-12"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend listenport",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 943.0, 906.0, 107.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-11"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend hostport",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 764.0, 910.0, 101.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-10"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "8000.",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 943.0, 858.0, 50.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-8"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "8080.",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 764.0, 862.0, 50.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-3"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 47.0, 270.0, 20.0, 20.0 ],
					"outlettype" : [ "bang" ],
					"id" : "obj-2"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend monome",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 1,
					"patching_rect" : [ 602.0, 912.0, 105.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-1"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 95.0, 1010.0, 25.0, 25.0 ],
					"id" : "obj-28",
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "OSC Listen Port",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"presentation_rect" : [ 158.0, 92.0, 82.0, 18.0 ],
					"numoutlets" : 0,
					"patching_rect" : [ 1009.0, 801.0, 102.0, 18.0 ],
					"fontname" : "Arial",
					"presentation" : 1,
					"id" : "obj-27"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "OSC Host Port",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"presentation_rect" : [ 46.0, 92.0, 78.0, 18.0 ],
					"numoutlets" : 0,
					"patching_rect" : [ 830.0, 800.0, 77.0, 18.0 ],
					"fontname" : "Arial",
					"presentation" : 1,
					"id" : "obj-26"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "OSC Host Address",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"presentation_rect" : [ 110.0, 69.0, 127.0, 18.0 ],
					"numoutlets" : 0,
					"patching_rect" : [ 537.0, 821.0, 126.0, 18.0 ],
					"fontname" : "Arial",
					"presentation" : 1,
					"id" : "obj-25"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "OSC Prefix",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"presentation_rect" : [ 110.0, 46.0, 129.0, 18.0 ],
					"numoutlets" : 0,
					"patching_rect" : [ 430.0, 773.0, 127.0, 18.0 ],
					"fontname" : "Arial",
					"presentation" : 1,
					"id" : "obj-24"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Monome Grid Size",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"presentation_rect" : [ 110.0, 24.0, 127.0, 18.0 ],
					"numoutlets" : 0,
					"patching_rect" : [ 608.0, 866.0, 125.0, 18.0 ],
					"fontname" : "Arial",
					"presentation" : 1,
					"id" : "obj-23"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "textedit",
					"varname" : "sevenup-address-ctrl",
					"text" : "Chaos-1.local.",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"presentation_rect" : [ 6.0, 68.0, 101.0, 20.0 ],
					"numoutlets" : 4,
					"patching_rect" : [ 429.0, 818.0, 101.0, 20.0 ],
					"fontname" : "Arial",
					"presentation" : 1,
					"outlettype" : [ "", "int", "", "" ],
					"id" : "obj-17"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "textedit",
					"varname" : "sevenup-prefix-ctrl",
					"text" : "23137up",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"presentation_rect" : [ 6.0, 44.0, 101.0, 20.0 ],
					"numoutlets" : 4,
					"patching_rect" : [ 286.0, 786.0, 101.0, 20.0 ],
					"fontname" : "Arial",
					"presentation" : 1,
					"outlettype" : [ "", "int", "", "" ],
					"id" : "obj-16"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.menu",
					"varname" : "sevenup-monome-ctrl",
					"numinlets" : 1,
					"presentation_rect" : [ 6.0, 25.0, 100.0, 15.0 ],
					"numoutlets" : 3,
					"patching_rect" : [ 606.0, 889.0, 100.0, 15.0 ],
					"pictures" : [  ],
					"presentation" : 1,
					"outlettype" : [ "", "", "float" ],
					"id" : "obj-15",
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.menu",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "sevenup-monome-ctrl",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "Monome64", "Monome128H", "Monome128V", "Monome3x64", "Monome256", "Monome2x256", "Monome3x256", "Monome5x64", "Monome6x64", "Monome7x64", "Monome8x64", "Monome9x64", "Monome10x64" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_initial" : [ 1.0 ],
							"parameter_type" : 2,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "start/stop",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"presentation_rect" : [ 27.0, 3.0, 107.0, 18.0 ],
					"numoutlets" : 0,
					"patching_rect" : [ 33.0, 69.0, 126.0, 18.0 ],
					"fontname" : "Arial",
					"presentation" : 1,
					"id" : "obj-9"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "shutdown",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 81.0, 247.0, 62.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-7"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "initialize",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"numoutlets" : 1,
					"patching_rect" : [ 23.0, 248.0, 53.0, 18.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "" ],
					"id" : "obj-6"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route 0 1",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"numoutlets" : 3,
					"patching_rect" : [ 75.0, 97.0, 58.0, 20.0 ],
					"fontname" : "Arial",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-5"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.toggle",
					"varname" : "live.toggle",
					"numinlets" : 1,
					"presentation_rect" : [ 6.0, 4.0, 15.0, 15.0 ],
					"numoutlets" : 1,
					"patching_rect" : [ 35.0, 94.0, 15.0, 15.0 ],
					"presentation" : 1,
					"outlettype" : [ "" ],
					"id" : "obj-4",
					"parameter_enable" : 1,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.toggle",
							"parameter_invisible" : 2,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.toggle",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 10,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "off", "on" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 1.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 2,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-65", 2 ],
					"destination" : [ "obj-51", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-65", 0 ],
					"destination" : [ "obj-52", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-65", 1 ],
					"destination" : [ "obj-67", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-65", 3 ],
					"destination" : [ "obj-66", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-70", 0 ],
					"destination" : [ "obj-65", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-74", 0 ],
					"destination" : [ "obj-65", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-71", 0 ],
					"destination" : [ "obj-70", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-69", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-69", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-68", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-68", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 0 ],
					"destination" : [ "obj-56", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-56", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-60", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-59", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-59", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-40", 0 ],
					"destination" : [ "obj-59", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-59", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-58", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-57", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-57", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-40", 0 ],
					"destination" : [ "obj-57", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-57", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-42", 0 ],
					"destination" : [ "obj-55", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 0 ],
					"destination" : [ "obj-55", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-52", 1 ],
					"destination" : [ "obj-17", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-49", 0 ],
					"destination" : [ "obj-62", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-62", 0 ],
					"destination" : [ "obj-37", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-50", 0 ],
					"destination" : [ "obj-62", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-63", 0 ],
					"destination" : [ "obj-62", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-44", 0 ],
					"destination" : [ "obj-49", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-47", 0 ],
					"destination" : [ "obj-45", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-33", 0 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 0 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-39", 0 ],
					"destination" : [ "obj-35", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-38", 0 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-39", 0 ],
					"destination" : [ "obj-42", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-38", 0 ],
					"destination" : [ "obj-43", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-39", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 1 ],
					"destination" : [ "obj-38", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-39", 0 ],
					"destination" : [ "obj-40", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-39", 0 ],
					"destination" : [ "obj-41", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-38", 0 ],
					"destination" : [ "obj-34", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-38", 0 ],
					"destination" : [ "obj-36", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 1 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-16", 0 ],
					"destination" : [ "obj-29", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-15", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-28", 0 ],
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
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-5", 0 ],
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
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-3", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-8", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-18", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-22", 1 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-31", 0 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-32", 0 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-39", 0 ],
					"destination" : [ "obj-32", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-38", 0 ],
					"destination" : [ "obj-31", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-45", 0 ],
					"destination" : [ "obj-46", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 1 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 0 ],
					"destination" : [ "obj-54", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-42", 0 ],
					"destination" : [ "obj-54", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-46", 0 ],
					"destination" : [ "obj-48", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-53", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-54", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-55", 0 ],
					"hidden" : 0,
					"midpoints" : [ 263.0, 290.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-48", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-34", 0 ],
					"destination" : [ "obj-48", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-48", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-40", 0 ],
					"destination" : [ "obj-48", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-20", 0 ],
					"destination" : [ "obj-56", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-22", 1 ],
					"destination" : [ "obj-52", 0 ],
					"hidden" : 0,
					"midpoints" : [ 494.5, 873.0, 398.0, 873.0, 398.0, 779.0, 415.5, 779.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 1 ],
					"destination" : [ "obj-51", 0 ],
					"hidden" : 0,
					"midpoints" : [ 332.5, 863.0, 204.0, 863.0, 204.0, 744.0, 237.5, 744.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-71", 0 ],
					"destination" : [ "obj-74", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-65::obj-275" : [ "monome.unit[1]", "monome.unit", 0 ],
			"obj-51" : [ "sevenup-prefix-ctrl[1]", "sevenup-prefix-ctrl[1]", 0 ],
			"obj-4" : [ "live.toggle", "live.toggle", 10 ],
			"obj-14" : [ "sevenup-osc-listen-port", "live.numbox", 0 ],
			"obj-15" : [ "sevenup-monome-ctrl", "live.menu", 0 ],
			"obj-13" : [ "sevenup-osc-host-port", "live.numbox", 0 ],
			"obj-74" : [ "sevenup-osc-autoconf-listen-port", "live.numbox", 0 ],
			"obj-52" : [ "sevenup-address-ctrl[1]", "sevenup-prefix-ctrl[2]", 0 ],
			"obj-70" : [ "live.text", "live.text", 0 ],
			"obj-37" : [ "live.grid", "live.grid", 0 ]
		}

	}

}
