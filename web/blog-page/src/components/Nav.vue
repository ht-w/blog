<template>
    <div>
      <v-row>
        <v-col cols="3">
        </v-col>
        <v-col>
            <div class="logo">康庭的博客
          <p class="logo-desc">Tech, Stories, Bits of My Life, and More.</p>
        </div>
        </v-col>
      </v-row>
<nav class="nav">
  
  <input class="nav__trigger-input" type="checkbox" id="trigger" aria-label="open the navigation"/>
  <label class="nav__trigger-finger" for="trigger">
    <span></span>
  </label>
  <ul class="nav__list">
    <li class="nav__item">
      <a href="/" class="nav__link">
        <span class="nav__text">
          Home 主页
        </span>
      </a>
    </li>
    <li class="nav__item">
      <a href="/about" class="nav__link">
        <span class="nav__text">
          About 关于我
        </span>
      </a>
      <!-- <input class="nav__submenu-trigger-input" type="checkbox" id="submenu-trigger" />
      <label class="nav__submenu-trigger-finger" for="submenu-trigger"></label> -->
        <!-- <ul class="nav__list-child">
          <li class="nav__item">
            <a href="#hoi" class="nav__link">
              <span class="nav__text">
                About me
              </span>
            </a>
          </li>
          <li class="nav__item">
            <a href="#hoi" class="nav__link">
              <span class="nav__text">
                About meerkats
              </span>
            </a>
          </li>
        </ul>
      </li> -->
    <li class="nav__item">
      <a href="/archives" class="nav__link">
        <span class="nav__text">
          Archives 归档
        </span>
      </a>
    </li>
     <li class="nav__item">
      <a href="/link" class="nav__link">
        <span class="nav__text">
          Links 链接
        </span>
      </a>
    </li>
    <li class="nav__item">
      <a href="/search" class="nav__link">
        <span class="nav__text">
          Search 搜索
        </span>
      </a>
    </li>
  </ul>
</nav>

  </div>

</template>

<style lang="scss" scoped>
$bezier: cubic-bezier(0.75, 0, 0.25, 1);
 .logo {
    color: #34495e;
   font-size: 40px;
   font-weight: 600;
   margin-top: 22px;
 }
 .logo-desc{
   font-size: 0.8rem;
   color:  rgba(88,110,117,1);
 }

.nav {
  color: white;
  text-align: center;

  &__trigger-finger {
    // The visible part of the trigger, this will be your "nav is closed!"-state
    position: fixed;
    top: 0;
    right: 0;
    z-index: 10;
    display: flex;

    align-items: center;
    width: 17px;
    height: 13px;
    margin: 40px;
    color: #34495e;
    &::before {
      content: '';
      position: absolute;
      top: -5px;
      left: -5px;
      bottom: -5px;
      right: -5px;
      background: #34495e;;
      opacity: 0;
      z-index: -1;
      transition: opacity 0.3s $bezier;
    }
    span {
      display: block;
      width: 100%;
      height: 2px;
      transition: transform 1s $bezier;
      background: currentColor;
      &::before,
      &::after {
        // Create the hamburger lines
        content: "";
        position: absolute;
        left: 0;
        right: 0;
        display: block; // Labels are inline elements, it needs a display property that allows it it's own set height like block;
        height: 2px;
        background: currentColor; // inherits color set on parent
        transition: transform 0.4s $bezier, top 0.4s 0.4s $bezier, background 0.2s $bezier;
      }
      &::before {
        top: 0;
      }
      &::after {
        top: calc(100% - 2px);
      }
    }
  }
  &__trigger-input, &__submenu-trigger-input {
    // We  want to make the input invisible without using display: none;, because that will remove functionality.
    opacity: 0;
    width: 0;
    height: 0;
    appearance: none;
    position: fixed;
  }
  &__trigger-input {
    &:focus {
      & + label {
        color: white;
        &::before {
          opacity: 1;
        }
      }
    }
    &:checked {
      & + label {
        // change the label if the input is checked, this will be your "nav is open! 🎉"-state
        height: 10px;
        color: white;        
        span {
          transform: rotate(45deg);
          &::before, &::after {
            top: calc(50% - 1px);
            transition: transform 0.4s 0.4s $bezier, top 0.4s $bezier, background 0.2s $bezier;
          }
          &::after {
            transform: rotate(90deg);
          }          
        }
      }
      & ~ ul {
        // We use a general sibling because it's less work, + label + ul would work as well
        z-index: 9;
        transform: none;
        transition: 0.5s $bezier;
      }
    }
  }
  &__list {
    // we want to make the top navigation items not visible while the input is not :checked.
    position: fixed;
    overflow-y: scroll;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    z-index: -1;
    background : #34495e;
    font-size: 1.32rem;
    padding: 40px;
    transform: translate(100vw) rotate(45deg);
    transition: 0.2s $bezier;
  }
  &__item {
    list-style: none;
    & + & {
      margin-top: 48px;
    }
  }
  &__submenu-trigger-input {
    &:checked {
      & + label::after {
        transform: translateY(10px);
      }
      & ~ ul {
        display: block;
      }
    }
    &:focus {
      & + label::after {
        transform: translateY(5px);
      }
    }
  }
  &__submenu-trigger-finger {
    &::after {
      content: '';
      display: inline-block;
      border: 10px solid transparent;
      border-top-color: white;
      transition: 0.4s $bezier;
    }
  } 
  &__link {
    text-decoration: none;
    color: inherit;
  }
  &__list-child {
    display: none;
    font-size: 2rem;
    margin-top: 10px;
    padding-left: 2rem;
  }

}
</style>