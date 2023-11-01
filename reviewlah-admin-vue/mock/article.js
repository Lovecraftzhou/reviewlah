const Mock = require('mockjs')
const {sentence} = require("mockjs/src/mock/random/text");

const List1 = []
const List2 = []
const count = 100
const dishesList = ['Grilled Pork', 'Roast Beijing Duck',
  'Braised Common Carp','Dumplings','Plum Juice',
  'Mapo Tofu','Steak','Hamburger']

function getRandomInt(max) {
  return Math.floor(Math.random() * max);
}

for (let i = 0; i < count; i++) {
  List1.push(Mock.mock({
    id: '@increment',
    menu_id: +Mock.Random.integer(1,100),
    timestamp: +Mock.Random.date('T'),
    username: '@first',
    phone : /^65\d{8}$/,
    author: '@first',
    email: '@email',
    reviewer: '@first',
    title: '@title(5, 8)',
    'identities|1': ["customer"],
    content_short: 'mock data',
    content: '@sentence(10,200)',
    forecast: '@float(0, 100, 2, 2)',
    importance: '@integer(1, 5)',
    'type|1': ['Chinese', 'Japanese', 'Korean'],
    'status|1': ['published', 'draft'],
    display_time: '@datetime(dd-MM-yyyy A HH:mm:ss)',
    comment_disabled: true,
    pageviews: '@integer(300, 5000)',
    platforms: ['a-platform'],
    dishes: dishesList.slice(getRandomInt(7),8)
  }))
}

for (let i = 0; i < count; i++) {
  List2.push(Mock.mock({
    id: '@increment',
    menu_id: +Mock.Random.integer(1,100),
    timestamp: +Mock.Random.date('T'),
    username: '@first',
    phone : /^65\d{8}$/,
    address_code : '@zip()',
    author: '@first',
    email: '@email',
    reviewer: '@first',
    title: '@title(5, 8)',
    'identities|1': ["merchants"],
    content_short: 'mock data',
    content: '@sentence(10,20)',
    forecast: '@float(0, 100, 2, 2)',
    importance: '@integer(1, 5)',
    'type|1': ['Chinese', 'Japanese', 'Korean'],
    'status|1': ['published', 'draft'],
    display_time: '@datetime(dd-MM-yyyy A HH:mm:ss)',
    comment_disabled: true,
    pageviews: '@integer(300, 5000)',
    platforms: ['a-platform']
  }))
}

module.exports = [
  {
    url: '/vue-element-admin/article/list',
    type: 'get',
    response: config => {
      const { importance, type, title, page = 1, limit = 20, sort } = config.query

      let mockList = List1.filter(item => {
        if (importance && item.importance !== +importance) return false
        if (type && item.type !== type) return false
        if (title && item.title.indexOf(title) < 0) return false
        return true
      })

      if (sort === '-id') {
        mockList = mockList.reverse()
      }

      const pageList = mockList.filter((item, index) => index < limit * page && index >= limit * (page - 1))

      return {
        code: 20000,
        data: {
          total: mockList.length,
          items: pageList
        }
      }
    }
  },

  {
    url: '/vue-element-admin/merchants/list',
    type: 'get',
    response: config => {
      const { importance, type, title, page = 1, limit = 20, sort } = config.query

      let mockList = List2.filter(item => {
        if (importance && item.importance !== +importance) return false
        if (type && item.type !== type) return false
        if (title && item.title.indexOf(title) < 0) return false
        return true
      })

      if (sort === '-id') {
        mockList = mockList.reverse()
      }

      const pageList = mockList.filter((item, index) => index < limit * page && index >= limit * (page - 1))

      return {
        code: 20000,
        data: {
          total: mockList.length,
          items: pageList
        }
      }
    }
  },

  {
    url: '/vue-element-admin/article/detail',
    type: 'get',
    response: config => {
      const { id } = config.query
      for (const article of List1) {
        if (article.id === +id) {
          return {
            code: 20000,
            data: article
          }
        }
      }
    }
  },

  {
    url: '/vue-element-admin/article/pv',
    type: 'get',
    response: _ => {
      return {
        code: 20000,
        data: {
          pvData: [
            { key: 'PC', pv: 1024 },
            { key: 'mobile', pv: 1024 },
            { key: 'ios', pv: 1024 },
            { key: 'android', pv: 1024 }
          ]
        }
      }
    }
  },

  {
    url: '/vue-element-admin/article/create',
    type: 'post',
    response: _ => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  },

  {
    url: '/vue-element-admin/article/update',
    type: 'post',
    response: _ => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  }
]

