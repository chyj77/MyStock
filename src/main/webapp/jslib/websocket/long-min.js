(function(global,factory){if(typeof define==='function'&&define["amd"])
define([],factory);else if(typeof require==='function'&&typeof module==="object"&&module&&module["exports"])
module["exports"]=factory();else
(global["dcodeIO"]=global["dcodeIO"]||{})["Long"]=factory();})(this,function(){"use strict";function Long(low,high,unsigned){this.low=low|0;this.high=high|0;this.unsigned=!!unsigned;}
Long.prototype.__isLong__;Object.defineProperty(Long.prototype,"__isLong__",{value:true,enumerable:false,configurable:false});function isLong(obj){return(obj&&obj["__isLong__"])===true;}
Long.isLong=isLong;var INT_CACHE={};var UINT_CACHE={};function fromInt(value,unsigned){var obj,cachedObj,cache;if(unsigned){value>>>=0;if(cache=(0<=value&&value<256)){cachedObj=UINT_CACHE[value];if(cachedObj)
return cachedObj;}
obj=fromBits(value,(value|0)<0?-1:0,true);if(cache)
UINT_CACHE[value]=obj;return obj;}else{value|=0;if(cache=(-128<=value&&value<128)){cachedObj=INT_CACHE[value];if(cachedObj)
return cachedObj;}
obj=fromBits(value,value<0?-1:0,false);if(cache)
INT_CACHE[value]=obj;return obj;}}
Long.fromInt=fromInt;function fromNumber(value,unsigned){if(isNaN(value)||!isFinite(value))
return unsigned?UZERO:ZERO;if(unsigned){if(value<0)
return UZERO;if(value>=TWO_PWR_64_DBL)
return MAX_UNSIGNED_VALUE;}else{if(value<=-TWO_PWR_63_DBL)
return MIN_VALUE;if(value+1>=TWO_PWR_63_DBL)
return MAX_VALUE;}
if(value<0)
return fromNumber(-value,unsigned).neg();return fromBits((value%TWO_PWR_32_DBL)|0,(value/TWO_PWR_32_DBL)|0,unsigned);}
Long.fromNumber=fromNumber;function fromBits(lowBits,highBits,unsigned){return new Long(lowBits,highBits,unsigned);}
Long.fromBits=fromBits;var pow_dbl=Math.pow;function fromString(str,unsigned,radix){if(str.length===0)
throw Error('empty string');if(str==="NaN"||str==="Infinity"||str==="+Infinity"||str==="-Infinity")
return ZERO;if(typeof unsigned==='number'){radix=unsigned,unsigned=false;}else{unsigned=!!unsigned;}
radix=radix||10;if(radix<2||36<radix)
throw RangeError('radix');var p;if((p=str.indexOf('-'))>0)
throw Error('interior hyphen');else if(p===0){return fromString(str.substring(1),unsigned,radix).neg();}
var radixToPower=fromNumber(pow_dbl(radix,8));var result=ZERO;for(var i=0;i<str.length;i+=8){var size=Math.min(8,str.length-i),value=parseInt(str.substring(i,i+size),radix);if(size<8){var power=fromNumber(pow_dbl(radix,size));result=result.mul(power).add(fromNumber(value));}else{result=result.mul(radixToPower);result=result.add(fromNumber(value));}}
result.unsigned=unsigned;return result;}
Long.fromString=fromString;function fromValue(val){if(val instanceof Long)
return val;if(typeof val==='number')
return fromNumber(val);if(typeof val==='string')
return fromString(val);return fromBits(val.low,val.high,val.unsigned);}
Long.fromValue=fromValue;var TWO_PWR_16_DBL=1<<16;var TWO_PWR_24_DBL=1<<24;var TWO_PWR_32_DBL=TWO_PWR_16_DBL*TWO_PWR_16_DBL;var TWO_PWR_64_DBL=TWO_PWR_32_DBL*TWO_PWR_32_DBL;var TWO_PWR_63_DBL=TWO_PWR_64_DBL/2;var TWO_PWR_24=fromInt(TWO_PWR_24_DBL);var ZERO=fromInt(0);Long.ZERO=ZERO;var UZERO=fromInt(0,true);Long.UZERO=UZERO;var ONE=fromInt(1);Long.ONE=ONE;var UONE=fromInt(1,true);Long.UONE=UONE;var NEG_ONE=fromInt(-1);Long.NEG_ONE=NEG_ONE;var MAX_VALUE=fromBits(0xFFFFFFFF|0,0x7FFFFFFF|0,false);Long.MAX_VALUE=MAX_VALUE;var MAX_UNSIGNED_VALUE=fromBits(0xFFFFFFFF|0,0xFFFFFFFF|0,true);Long.MAX_UNSIGNED_VALUE=MAX_UNSIGNED_VALUE;var MIN_VALUE=fromBits(0,0x80000000|0,false);Long.MIN_VALUE=MIN_VALUE;var LongPrototype=Long.prototype;LongPrototype.toInt=function toInt(){return this.unsigned?this.low>>>0:this.low;};LongPrototype.toNumber=function toNumber(){if(this.unsigned)
return((this.high>>>0)*TWO_PWR_32_DBL)+(this.low>>>0);return this.high*TWO_PWR_32_DBL+(this.low>>>0);};LongPrototype.toString=function toString(radix){radix=radix||10;if(radix<2||36<radix)
throw RangeError('radix');if(this.isZero())
return'0';if(this.isNegative()){if(this.eq(MIN_VALUE)){var radixLong=fromNumber(radix),div=this.div(radixLong),rem1=div.mul(radixLong).sub(this);return div.toString(radix)+rem1.toInt().toString(radix);}else
return'-'+this.neg().toString(radix);}
var radixToPower=fromNumber(pow_dbl(radix,6),this.unsigned),rem=this;var result='';while(true){var remDiv=rem.div(radixToPower),intval=rem.sub(remDiv.mul(radixToPower)).toInt()>>>0,digits=intval.toString(radix);rem=remDiv;if(rem.isZero())
return digits+result;else{while(digits.length<6)
digits='0'+digits;result=''+digits+result;}}};LongPrototype.getHighBits=function getHighBits(){return this.high;};LongPrototype.getHighBitsUnsigned=function getHighBitsUnsigned(){return this.high>>>0;};LongPrototype.getLowBits=function getLowBits(){return this.low;};LongPrototype.getLowBitsUnsigned=function getLowBitsUnsigned(){return this.low>>>0;};LongPrototype.getNumBitsAbs=function getNumBitsAbs(){if(this.isNegative())
return this.eq(MIN_VALUE)?64:this.neg().getNumBitsAbs();var val=this.high!=0?this.high:this.low;for(var bit=31;bit>0;bit--)
if((val&(1<<bit))!=0)
break;return this.high!=0?bit+33:bit+1;};LongPrototype.isZero=function isZero(){return this.high===0&&this.low===0;};LongPrototype.isNegative=function isNegative(){return!this.unsigned&&this.high<0;};LongPrototype.isPositive=function isPositive(){return this.unsigned||this.high>=0;};LongPrototype.isOdd=function isOdd(){return(this.low&1)===1;};LongPrototype.isEven=function isEven(){return(this.low&1)===0;};LongPrototype.equals=function equals(other){if(!isLong(other))
other=fromValue(other);if(this.unsigned!==other.unsigned&&(this.high>>>31)===1&&(other.high>>>31)===1)
return false;return this.high===other.high&&this.low===other.low;};LongPrototype.eq=LongPrototype.equals;LongPrototype.notEquals=function notEquals(other){return!this.eq(other);};LongPrototype.neq=LongPrototype.notEquals;LongPrototype.lessThan=function lessThan(other){return this.comp(other)<0;};LongPrototype.lt=LongPrototype.lessThan;LongPrototype.lessThanOrEqual=function lessThanOrEqual(other){return this.comp(other)<=0;};LongPrototype.lte=LongPrototype.lessThanOrEqual;LongPrototype.greaterThan=function greaterThan(other){return this.comp(other)>0;};LongPrototype.gt=LongPrototype.greaterThan;LongPrototype.greaterThanOrEqual=function greaterThanOrEqual(other){return this.comp(other)>=0;};LongPrototype.gte=LongPrototype.greaterThanOrEqual;LongPrototype.compare=function compare(other){if(!isLong(other))
other=fromValue(other);if(this.eq(other))
return 0;var thisNeg=this.isNegative(),otherNeg=other.isNegative();if(thisNeg&&!otherNeg)
return-1;if(!thisNeg&&otherNeg)
return 1;if(!this.unsigned)
return this.sub(other).isNegative()?-1:1;return(other.high>>>0)>(this.high>>>0)||(other.high===this.high&&(other.low>>>0)>(this.low>>>0))?-1:1;};LongPrototype.comp=LongPrototype.compare;LongPrototype.negate=function negate(){if(!this.unsigned&&this.eq(MIN_VALUE))
return MIN_VALUE;return this.not().add(ONE);};LongPrototype.neg=LongPrototype.negate;LongPrototype.add=function add(addend){if(!isLong(addend))
addend=fromValue(addend);var a48=this.high>>>16;var a32=this.high&0xFFFF;var a16=this.low>>>16;var a00=this.low&0xFFFF;var b48=addend.high>>>16;var b32=addend.high&0xFFFF;var b16=addend.low>>>16;var b00=addend.low&0xFFFF;var c48=0,c32=0,c16=0,c00=0;c00+=a00+b00;c16+=c00>>>16;c00&=0xFFFF;c16+=a16+b16;c32+=c16>>>16;c16&=0xFFFF;c32+=a32+b32;c48+=c32>>>16;c32&=0xFFFF;c48+=a48+b48;c48&=0xFFFF;return fromBits((c16<<16)|c00,(c48<<16)|c32,this.unsigned);};LongPrototype.subtract=function subtract(subtrahend){if(!isLong(subtrahend))
subtrahend=fromValue(subtrahend);return this.add(subtrahend.neg());};LongPrototype.sub=LongPrototype.subtract;LongPrototype.multiply=function multiply(multiplier){if(this.isZero())
return ZERO;if(!isLong(multiplier))
multiplier=fromValue(multiplier);if(multiplier.isZero())
return ZERO;if(this.eq(MIN_VALUE))
return multiplier.isOdd()?MIN_VALUE:ZERO;if(multiplier.eq(MIN_VALUE))
return this.isOdd()?MIN_VALUE:ZERO;if(this.isNegative()){if(multiplier.isNegative())
return this.neg().mul(multiplier.neg());else
return this.neg().mul(multiplier).neg();}else if(multiplier.isNegative())
return this.mul(multiplier.neg()).neg();if(this.lt(TWO_PWR_24)&&multiplier.lt(TWO_PWR_24))
return fromNumber(this.toNumber()*multiplier.toNumber(),this.unsigned);var a48=this.high>>>16;var a32=this.high&0xFFFF;var a16=this.low>>>16;var a00=this.low&0xFFFF;var b48=multiplier.high>>>16;var b32=multiplier.high&0xFFFF;var b16=multiplier.low>>>16;var b00=multiplier.low&0xFFFF;var c48=0,c32=0,c16=0,c00=0;c00+=a00*b00;c16+=c00>>>16;c00&=0xFFFF;c16+=a16*b00;c32+=c16>>>16;c16&=0xFFFF;c16+=a00*b16;c32+=c16>>>16;c16&=0xFFFF;c32+=a32*b00;c48+=c32>>>16;c32&=0xFFFF;c32+=a16*b16;c48+=c32>>>16;c32&=0xFFFF;c32+=a00*b32;c48+=c32>>>16;c32&=0xFFFF;c48+=a48*b00+a32*b16+a16*b32+a00*b48;c48&=0xFFFF;return fromBits((c16<<16)|c00,(c48<<16)|c32,this.unsigned);};LongPrototype.mul=LongPrototype.multiply;LongPrototype.divide=function divide(divisor){if(!isLong(divisor))
divisor=fromValue(divisor);if(divisor.isZero())
throw Error('division by zero');if(this.isZero())
return this.unsigned?UZERO:ZERO;var approx,rem,res;if(!this.unsigned){if(this.eq(MIN_VALUE)){if(divisor.eq(ONE)||divisor.eq(NEG_ONE))
return MIN_VALUE;else if(divisor.eq(MIN_VALUE))
return ONE;else{var halfThis=this.shr(1);approx=halfThis.div(divisor).shl(1);if(approx.eq(ZERO)){return divisor.isNegative()?ONE:NEG_ONE;}else{rem=this.sub(divisor.mul(approx));res=approx.add(rem.div(divisor));return res;}}}else if(divisor.eq(MIN_VALUE))
return this.unsigned?UZERO:ZERO;if(this.isNegative()){if(divisor.isNegative())
return this.neg().div(divisor.neg());return this.neg().div(divisor).neg();}else if(divisor.isNegative())
return this.div(divisor.neg()).neg();res=ZERO;}else{if(!divisor.unsigned)
divisor=divisor.toUnsigned();if(divisor.gt(this))
return UZERO;if(divisor.gt(this.shru(1)))
return UONE;res=UZERO;}
rem=this;while(rem.gte(divisor)){approx=Math.max(1,Math.floor(rem.toNumber()/divisor.toNumber()));var log2=Math.ceil(Math.log(approx)/Math.LN2),delta=(log2<=48)?1:pow_dbl(2,log2-48),approxRes=fromNumber(approx),approxRem=approxRes.mul(divisor);while(approxRem.isNegative()||approxRem.gt(rem)){approx-=delta;approxRes=fromNumber(approx,this.unsigned);approxRem=approxRes.mul(divisor);}
if(approxRes.isZero())
approxRes=ONE;res=res.add(approxRes);rem=rem.sub(approxRem);}
return res;};LongPrototype.div=LongPrototype.divide;LongPrototype.modulo=function modulo(divisor){if(!isLong(divisor))
divisor=fromValue(divisor);return this.sub(this.div(divisor).mul(divisor));};LongPrototype.mod=LongPrototype.modulo;LongPrototype.not=function not(){return fromBits(~this.low,~this.high,this.unsigned);};LongPrototype.and=function and(other){if(!isLong(other))
other=fromValue(other);return fromBits(this.low&other.low,this.high&other.high,this.unsigned);};LongPrototype.or=function or(other){if(!isLong(other))
other=fromValue(other);return fromBits(this.low|other.low,this.high|other.high,this.unsigned);};LongPrototype.xor=function xor(other){if(!isLong(other))
other=fromValue(other);return fromBits(this.low^other.low,this.high^other.high,this.unsigned);};LongPrototype.shiftLeft=function shiftLeft(numBits){if(isLong(numBits))
numBits=numBits.toInt();if((numBits&=63)===0)
return this;else if(numBits<32)
return fromBits(this.low<<numBits,(this.high<<numBits)|(this.low>>>(32-numBits)),this.unsigned);else
return fromBits(0,this.low<<(numBits-32),this.unsigned);};LongPrototype.shl=LongPrototype.shiftLeft;LongPrototype.shiftRight=function shiftRight(numBits){if(isLong(numBits))
numBits=numBits.toInt();if((numBits&=63)===0)
return this;else if(numBits<32)
return fromBits((this.low>>>numBits)|(this.high<<(32-numBits)),this.high>>numBits,this.unsigned);else
return fromBits(this.high>>(numBits-32),this.high>=0?0:-1,this.unsigned);};LongPrototype.shr=LongPrototype.shiftRight;LongPrototype.shiftRightUnsigned=function shiftRightUnsigned(numBits){if(isLong(numBits))
numBits=numBits.toInt();numBits&=63;if(numBits===0)
return this;else{var high=this.high;if(numBits<32){var low=this.low;return fromBits((low>>>numBits)|(high<<(32-numBits)),high>>>numBits,this.unsigned);}else if(numBits===32)
return fromBits(high,0,this.unsigned);else
return fromBits(high>>>(numBits-32),0,this.unsigned);}};LongPrototype.shru=LongPrototype.shiftRightUnsigned;LongPrototype.toSigned=function toSigned(){if(!this.unsigned)
return this;return fromBits(this.low,this.high,false);};LongPrototype.toUnsigned=function toUnsigned(){if(this.unsigned)
return this;return fromBits(this.low,this.high,true);};LongPrototype.toBytes=function(le){return le?this.toBytesLE():this.toBytesBE();}
LongPrototype.toBytesLE=function(){var hi=this.high,lo=this.low;return[lo&0xff,(lo>>>8)&0xff,(lo>>>16)&0xff,(lo>>>24)&0xff,hi&0xff,(hi>>>8)&0xff,(hi>>>16)&0xff,(hi>>>24)&0xff];}
LongPrototype.toBytesBE=function(){var hi=this.high,lo=this.low;return[(hi>>>24)&0xff,(hi>>>16)&0xff,(hi>>>8)&0xff,hi&0xff,(lo>>>24)&0xff,(lo>>>16)&0xff,(lo>>>8)&0xff,lo&0xff];}
return Long;});