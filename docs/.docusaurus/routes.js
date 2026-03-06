import React from 'react';
import ComponentCreator from '@docusaurus/ComponentCreator';

export default [
  {
    path: '/DeskUI/markdown-page',
    component: ComponentCreator('/DeskUI/markdown-page', '6e4'),
    exact: true
  },
  {
    path: '/DeskUI/docs',
    component: ComponentCreator('/DeskUI/docs', '476'),
    routes: [
      {
        path: '/DeskUI/docs',
        component: ComponentCreator('/DeskUI/docs', 'ea1'),
        routes: [
          {
            path: '/DeskUI/docs',
            component: ComponentCreator('/DeskUI/docs', 'b83'),
            routes: [
              {
                path: '/DeskUI/docs/components/avatar',
                component: ComponentCreator('/DeskUI/docs/components/avatar', 'c55'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/button',
                component: ComponentCreator('/DeskUI/docs/components/button', '23d'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/card',
                component: ComponentCreator('/DeskUI/docs/components/card', '0db'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/checkbox',
                component: ComponentCreator('/DeskUI/docs/components/checkbox', '63c'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/filter-chip',
                component: ComponentCreator('/DeskUI/docs/components/filter-chip', 'fc6'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/icon-box',
                component: ComponentCreator('/DeskUI/docs/components/icon-box', 'a2c'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/list-item',
                component: ComponentCreator('/DeskUI/docs/components/list-item', 'a49'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/search-bar',
                component: ComponentCreator('/DeskUI/docs/components/search-bar', 'e32'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/section-header',
                component: ComponentCreator('/DeskUI/docs/components/section-header', '00e'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/select',
                component: ComponentCreator('/DeskUI/docs/components/select', 'bd4'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/status-badge',
                component: ComponentCreator('/DeskUI/docs/components/status-badge', '92e'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/components/text-field',
                component: ComponentCreator('/DeskUI/docs/components/text-field', '2da'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/getting-started',
                component: ComponentCreator('/DeskUI/docs/getting-started', '9f1'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/layout/bottom-bar',
                component: ComponentCreator('/DeskUI/docs/layout/bottom-bar', '2a7'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/layout/navbar',
                component: ComponentCreator('/DeskUI/docs/layout/navbar', '7b0'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/layout/page-header',
                component: ComponentCreator('/DeskUI/docs/layout/page-header', '99b'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/layout/scaffold',
                component: ComponentCreator('/DeskUI/docs/layout/scaffold', '250'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/layout/sidebar',
                component: ComponentCreator('/DeskUI/docs/layout/sidebar', '92b'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/layout/tab-bar',
                component: ComponentCreator('/DeskUI/docs/layout/tab-bar', 'ebd'),
                exact: true,
                sidebar: "docsSidebar"
              },
              {
                path: '/DeskUI/docs/theme',
                component: ComponentCreator('/DeskUI/docs/theme', '1a1'),
                exact: true,
                sidebar: "docsSidebar"
              }
            ]
          }
        ]
      }
    ]
  },
  {
    path: '/DeskUI/',
    component: ComponentCreator('/DeskUI/', 'a3a'),
    exact: true
  },
  {
    path: '*',
    component: ComponentCreator('*'),
  },
];
